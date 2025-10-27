package hdli;

import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.functions.RuntimeContext;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.cdc.connectors.mysql.source.MySqlSource;
import org.apache.flink.cdc.debezium.JsonDebeziumDeserializationSchema;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.elasticsearch.RequestIndexer;
import org.apache.flink.streaming.connectors.elasticsearch8.ElasticsearchSink;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;
import org.apache.http.HttpHost;
import org.elasticsearch.client.Requests;
import org.elasticsearch.xcontent.XContentType;

import java.util.Collections;
import java.util.Properties;

/**
 * Description:
 *
 * @author: hendrix.li
 * @date: 2025-10-18 02:15
 */
public class Job {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);

        // 2. 配置MySQL CDC源
        MySqlSource<String> mysqlSource = MySqlSource
                .<String>builder()
                .hostname("localhost")
                .port(3306)
                .databaseList("work_db")
                .tableList("work_db.work_item")
                .username("root")
                .password("12345678")
                .deserializer(new JsonDebeziumDeserializationSchema())
                .includeSchemaChanges(true)
                .build();

        env.enableCheckpointing(3000);
        env.getCheckpointConfig().setCheckpointTimeout(10000);
        DataStreamSource<String> stringDataStreamSource = env.fromSource(mysqlSource, WatermarkStrategy.noWatermarks(), "MySQL Source")
                .setParallelism(2);


        // 3. 转换变更事件为ES格式
//        DataStream<WorkItemEvent> workItemStream = env.fromSource(mysqlSource, WatermarkStrategy.noWatermarks(), "MySQL-Source")
//                .map(event -> {
//                    WorkItemEvent e = new WorkItemEvent();
//                    e.setOperation(event.operation()); // INSERT/UPDATE/DELETE
//                    e.setData(event.after());          // 变更后的数据
//                    e.setVersion(event.after().getVersion()); // 版本号
//                    e.setLsn(event.source().getLsn());       // binlog位点
//                    return e;
//                });

//        stringDataStreamSource.print().setParallelism(1);
//
//        stringDataStreamSource.map(e -> {
////            System.out.println(e);
//            return e;
//        });

        // 写入Kafka
        stringDataStreamSource.addSink(
                new FlinkKafkaProducer<>(
                        "quickstart-events",
                        new SimpleStringSchema(),
                        new Properties() {{
                            put("bootstrap.servers", "localhost:9092");
                        }}
                )
        ).name("Kafka Sink");

        // 从Kafka读取并写入Elasticsearch
        FlinkKafkaConsumer<String> kafkaSource = new FlinkKafkaConsumer<>(
                "quickstart-events",
                new SimpleStringSchema(),
                new Properties() {{
                    put("bootstrap.servers", "localhost:9092");
                    put("group.id", "flink_consumer");
                }}
        );

        kafkaSource.setStartFromLatest();
        DataStream<String> kafkaStream = env.addSource(kafkaSource);

        kafkaStream.addSink(
                new ElasticsearchSink.Builder<String>(
                        Collections.singletonList(new HttpHost("localhost", 9200, "http")),
                        (String element, RuntimeContext ctx, RequestIndexer indexer) -> {
                            indexer.add(Requests.indexRequest()
                                    .index("workitem")
                                    .source(element, XContentType.JSON));
                        }
                ).build()
        ).name("Elasticsearch Sink");

        env.execute("test");
    }
}
