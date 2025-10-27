package hdli.simple;

import org.apache.rocketmq.client.apis.ClientConfiguration;
import org.apache.rocketmq.client.apis.ClientException;
import org.apache.rocketmq.client.apis.ClientServiceProvider;
import org.apache.rocketmq.client.apis.consumer.ConsumeResult;
import org.apache.rocketmq.client.apis.consumer.FilterExpression;
import org.apache.rocketmq.client.apis.consumer.FilterExpressionType;
import org.apache.rocketmq.client.apis.consumer.PushConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.util.Collections;

/**
 * Description:
 *
 * @author: hendrix.li
 * @date: 2025-10-16 21:00
 */
public class PushConsumerExample {
    private static final Logger logger = LoggerFactory.getLogger(PushConsumerExample.class);

    private PushConsumerExample() {
    }

    public static void main(String[] args) {
        try {
            final ClientServiceProvider provider = ClientServiceProvider.loadService();
            String endpoints = "localhost:8081";
            ClientConfiguration clientConfiguration = ClientConfiguration.newBuilder().setEndpoints(endpoints).build();


            String tag = "2";
            FilterExpression filterExpression = new FilterExpression(tag, FilterExpressionType.TAG);

            String consumerGroup = "YourConsumerGroup";
            String topic = "TestTopic";
            PushConsumer pushConsumer = provider.newPushConsumerBuilder()
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
            // 如果不需要再使用 PushConsumer，可关闭该实例。
//            pushConsumer.close();
        } catch (ClientException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
//        catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }
}
