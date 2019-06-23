package hdli.amqp;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author: Lihuaidong
 * @Date: Created at 14:54 2019/5/18
 */
@Component
@RabbitListener(queues = {"topic_messages"})
public class Receiver2 {

	@RabbitHandler
	public void process(String hello) {
		System.out.println("Receiver2:" + hello);
	}
}
