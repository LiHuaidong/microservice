package hdli.rabbitmqdemo;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author: Lihuaidong
 * @Date: Created at 16:46 2018/10/25
 */
@Component
@RabbitListener(queues = "hello")
public class Receiver {

	@RabbitHandler
	public void process(String hello) {
		System.out.println("Receiver : " + hello);
	}
}
