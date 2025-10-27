package hdli.delay;

import org.apache.rocketmq.client.apis.ClientConfiguration;
import org.apache.rocketmq.client.apis.ClientException;
import org.apache.rocketmq.client.apis.ClientServiceProvider;
import org.apache.rocketmq.client.apis.consumer.FilterExpression;
import org.apache.rocketmq.client.apis.consumer.FilterExpressionType;
import org.apache.rocketmq.client.apis.consumer.SimpleConsumer;
import org.apache.rocketmq.client.apis.message.MessageView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.Duration;
import java.util.Collections;
import java.util.List;

/**
 * Description:
 *
 * @author: hendrix.li
 * @date: 2025-10-16 22:38
 */
public class ConsumerClient2 {

    private static final Logger logger = LoggerFactory.getLogger(ConsumerClient2.class);

    public static void main(String[] args) throws IOException {
        final ClientServiceProvider provider = ClientServiceProvider.loadService();
        String endpoints = "localhost:8081";
        ClientConfiguration clientConfiguration = ClientConfiguration.newBuilder().setEndpoints(endpoints).build();

        SimpleConsumer simpleConsumer = null;
        String tag = "messageTag";
        String topic = "TestTopic";
        String consumerGroup = "YourConsumerGroup";

        FilterExpression filterExpression = new FilterExpression(tag, FilterExpressionType.TAG);
        List<MessageView> messageViewList = null;
        try {
            simpleConsumer = provider.newSimpleConsumerBuilder()
                    .setClientConfiguration(clientConfiguration)
                    .setConsumerGroup(consumerGroup)
                    .setSubscriptionExpressions(Collections.singletonMap(topic, filterExpression))
                    .build();


            messageViewList = simpleConsumer.receive(10, Duration.ofSeconds(30));
            SimpleConsumer finalSimpleConsumer = simpleConsumer;
            messageViewList.forEach(messageView -> {
                System.out.println(messageView);
                //消费处理完成后，需要主动调用ACK提交消费结果。
                try {
                    finalSimpleConsumer.ack(messageView);
                } catch (ClientException e) {
                    e.printStackTrace();
                }
            });
        } catch (ClientException e) {
            //如果遇到系统流控等原因造成拉取失败，需要重新发起获取消息请求。
            e.printStackTrace();
        } finally {
            simpleConsumer.close();
        }
    }
}

