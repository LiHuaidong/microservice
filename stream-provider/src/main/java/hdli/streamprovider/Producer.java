package hdli.streamprovider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @Description
 * @Author: Lihuaidong
 * @Date: Created at 18:16 2019/5/27
 */
@EnableBinding({CustomSource.class})
public class Producer {

	@Autowired
	private CustomSource customSource;

	@Scheduled(fixedRate = 5000)
	public void produceHotDrinks() {
		customSource.output().send(MessageBuilder.withPayload(new CartDTO("produceHotDrinks", 1)).build());
	}

	@Scheduled(fixedRate = 3000)
	public void produceColdDrinks() {
		customSource.output().send(MessageBuilder.withPayload(new CartDTO("produceColdDrinks", 1)).build());
	}

	@Scheduled(fixedRate = 3000)
	public void produceItem() {
		customSource.output1().send(MessageBuilder.withPayload(new CartDTO("produceItem", 1)).build());
	}

	@Scheduled(fixedRate = 3000)
	public void produceMsg() {
		customSource.output2().send(MessageBuilder.withPayload("produceMsg").build());
	}

}
