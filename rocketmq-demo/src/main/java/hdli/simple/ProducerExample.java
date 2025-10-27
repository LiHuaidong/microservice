package hdli.simple;

import org.apache.rocketmq.client.apis.ClientConfiguration;
import org.apache.rocketmq.client.apis.ClientConfigurationBuilder;
import org.apache.rocketmq.client.apis.ClientException;
import org.apache.rocketmq.client.apis.ClientServiceProvider;
import org.apache.rocketmq.client.apis.message.Message;
import org.apache.rocketmq.client.apis.producer.Producer;
import org.apache.rocketmq.client.apis.producer.SendReceipt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;

/**
 * Description:
 *
 * @author: hendrix.li
 * @date: 2025-10-16 20:44
 */
public class ProducerExample {

    private static final Logger logger = LoggerFactory.getLogger(ProducerExample.class);

    public static void main(String[] args) {
        String endpoint = "localhost:8081";
        String topic = "TestTopic";
        ClientServiceProvider provider = ClientServiceProvider.loadService();
        ClientConfigurationBuilder builder = ClientConfiguration.newBuilder().setEndpoints(endpoint);
        ClientConfiguration configuration = builder.build();

        try {
            Producer producer = provider.newProducerBuilder()
                    .setClientConfiguration(configuration)
                    .setTopics(topic)
                    .build();

            Message message = provider.newMessageBuilder()
                    .setTopic(topic)
                    .setTag("2")
                    .setKeys("messageTag")
                    .setBody("messageBody1".getBytes(StandardCharsets.UTF_8))
                    .build();

            SendReceipt sendReceipt = producer.send(message);
            logger.info("Send message successfully, messageId={}", sendReceipt.getMessageId());
            // producer.close();
        } catch (ClientException e) {
            logger.error("Failed to send message", e);
        }
//        catch (IOException e) {
//            throw new RuntimeException(e);
//        }

    }
}
