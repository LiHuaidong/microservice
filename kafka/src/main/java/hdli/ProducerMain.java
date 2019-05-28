package hdli;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * Hello world!
 *
 */
public class ProducerMain
{
    public static void main( String[] args ) {
        Properties pros = new Properties();
        pros.put("bootstrap.servers", "elasticsearch:9092");
        pros.put("acks", "all");
        pros.put("retries", 0);
        pros.put("batch.size", 16384);
        pros.put("linger.ms", 1);
        pros.put("buffer.memory", 33554432);
        pros.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        pros.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        KafkaProducer<String, String> producer = new KafkaProducer<>(pros);
        for (int i = 0; i < 100; i++) {
            producer.send(new ProducerRecord("test-topic", i + "", "hdli1 " + i));
        }
        producer.close();
    }
}
