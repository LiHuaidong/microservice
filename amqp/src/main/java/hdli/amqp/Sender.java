package hdli.amqp;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @Description
 * @Author: Lihuaidong
 * @Date: Created at 14:30 2019/5/18
 */
@Component
public class Sender {

	@Autowired
	private AmqpTemplate rabbitmqTemplate;

	public void send() {
		String content = "hello " + LocalDateTime.now().toString();
		System.out.println("Sender:" + content);
		this.rabbitmqTemplate.convertAndSend("hello", content);
		this.rabbitmqTemplate.convertAndSend("exchage","topic.message","topic_message");
		this.rabbitmqTemplate.convertAndSend("exchage","topic.messages","topic_messages");
	}

}
