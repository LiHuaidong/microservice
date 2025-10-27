package hdli.delay;

import org.apache.rocketmq.client.apis.ClientConfiguration;
import org.apache.rocketmq.client.apis.ClientServiceProvider;
import org.apache.rocketmq.client.apis.consumer.ConsumeResult;
import org.apache.rocketmq.client.apis.consumer.FilterExpression;
import org.apache.rocketmq.client.apis.consumer.FilterExpressionType;
import org.apache.rocketmq.client.apis.consumer.PushConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;

/**
 * Description:
 *
 * @author: hendrix.li
 * @date: 2025-10-16 22:38
 */
public class ConsumerClient1 {

    private static final Logger logger = LoggerFactory.getLogger(ConsumerClient1.class);

    public static void main(String[] args) throws IOException {
        PushConsumer pushConsumer = null;
        try {
            final ClientServiceProvider provider = ClientServiceProvider.loadService();
            String endpoints = "localhost:8081";
            ClientConfiguration clientConfiguration = ClientConfiguration.newBuilder().setEndpoints(endpoints).build();

            String tag = "messageTag";
            String topic = "TestTopic";
            String consumerGroup = "YourConsumerGroup";

            FilterExpression filterExpression = new FilterExpression(tag, FilterExpressionType.TAG);
            pushConsumer = provider.newPushConsumerBuilder()
                    .setClientConfiguration(clientConfiguration)
                    // 设置消费者分组。
                    .setConsumerGroup(consumerGroup)
                    // 设置预绑定的订阅关系。
                    .setSubscriptionExpressions(Collections.singletonMap(topic, filterExpression))
                    .setMessageListener(messageView -> {
                        String messageBody = new String(messageView.getBody().array(), StandardCharsets.UTF_8);
                        logger.info("Consume message successfully, messageId={}, body={}", messageView.getMessageId(), messageBody);
                        return ConsumeResult.SUCCESS;
                    }).build();
            Thread.sleep(Long.MAX_VALUE);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (pushConsumer != null) {
                pushConsumer.close();
            }
        }
    }
}
