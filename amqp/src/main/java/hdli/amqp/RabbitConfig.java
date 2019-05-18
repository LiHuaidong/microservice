package hdli.amqp;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description
 * @Author: Lihuaidong
 * @Date: Created at 15:05 2019/5/18
 */
@Configuration
public class RabbitConfig {

	@Bean
	public Queue helloQueue(){
		return new Queue("hello");
	}

	@Bean
	public Queue queueMessage(){
		return  new Queue("topic_message");
	}

	@Bean
	public Queue queueMessages(){
		return  new Queue("topic_messages");
	}

	@Bean TopicExchange exchange(){
		return new TopicExchange("exchage");
	}

	@Bean
	Binding bindingExchangeMessage(Queue queueMessage, TopicExchange exchange){
		Binding result = BindingBuilder.bind(queueMessage).to(exchange).with("topic.message");
		return result;
	}

	//Topic交换机的绑定
	@Bean
	Binding bindingExchangeMessages(Queue queueMessages, TopicExchange exchange){
		return BindingBuilder.bind(queueMessages).to(exchange).with("topic.#");
	}

//	@Bean
//	Binding bindingFanoutExchangeMessage(Queue queueMessage, TopicExchange exchange){
//		return BindingBuilder.bind(queueMessage).to(exchange);
//	}

}
