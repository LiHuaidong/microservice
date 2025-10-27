package hdli.delay;

import org.apache.rocketmq.client.apis.ClientConfiguration;
import org.apache.rocketmq.client.apis.ClientConfigurationBuilder;
import org.apache.rocketmq.client.apis.ClientServiceProvider;
import org.apache.rocketmq.client.apis.message.Message;
import org.apache.rocketmq.client.apis.message.MessageBuilder;
import org.apache.rocketmq.client.apis.producer.Producer;
import org.apache.rocketmq.client.apis.producer.SendReceipt;
import org.apache.rocketmq.client.java.message.MessageBuilderImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;

/**
 * Description:
 *
 * @author: hendrix.li
 * @date: 2025-10-16 22:31
 */
public class ProducerClient {
    private static final Logger logger = LoggerFactory.getLogger(ProducerClient.class);

    public static void main(String[] args) {
        String endpoint = "localhost:8081";
        String topic = "DelayTopic";
        String key = "messageKey";
        String body = "messageBody";
        String tag = "messageTag";
        ClientServiceProvider provider = ClientServiceProvider.loadService();
        ClientConfigurationBuilder builder = ClientConfiguration.newBuilder().setEndpoints(endpoint);
        ClientConfiguration configuration = builder.build();

        try {
            Producer producer = provider.newProducerBuilder()
                    .setClientConfiguration(configuration)
                    .setTopics(topic)
                    .build();

            Long deliveryTime = System.currentTimeMillis() + 10000;
            MessageBuilder messageBuilder = new MessageBuilderImpl();
            Message message = messageBuilder
                    .setTopic(topic)
                    .setKeys(key)
                    .setTag(tag)
                    .setBody(body.getBytes(StandardCharsets.UTF_8))
                    .setDeliveryTimestamp(deliveryTime)
                    .build();

            SendReceipt sendReceipt = producer.send(message);
            System.out.println(sendReceipt.getMessageId());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
