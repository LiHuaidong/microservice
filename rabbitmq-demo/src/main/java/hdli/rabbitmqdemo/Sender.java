package hdli.rabbitmqdemo;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Description
 * @Author: Lihuaidong
 * @Date: Created at 16:43 2018/10/25
 */
@Component
public class Sender {

	@Autowired
	private AmqpTemplate amqpTemplate;

	public void send() {
		String context = "hello " + new Date();
		System.out.println("Sender : " + context);
		amqpTemplate.convertAndSend("hello", context);
	}
}
