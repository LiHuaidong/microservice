package hdli.streamprovider;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.support.GenericMessage;

/**
 * @Description
 * @Author: Lihuaidong
 * @Date: Created at 17:24 2019/5/27
 */
@EnableBinding({ CustomSink.class })
public class Consumer {

	@StreamListener(CustomSink.INPUT)
	public synchronized void listen_average(CartDTO cartDTO) {
		System.out.println("Order Received For Average : " + cartDTO);
	}

	@StreamListener(CustomSink.INPUT0)
	public synchronized void listen_hdfsWrite(CartDTO cartDTO) {
		System.out.println("Order Received For hdfsWrite : " + cartDTO);
	}


	@StreamListener(CustomSink.INPUT1)
	public synchronized void receive(CartDTO cartDTO) {
		System.out.println("Item Received: " + cartDTO);
	}

	@StreamListener(CustomSink.INPUT2)
	public synchronized <T> void get(GenericMessage<T> msg) {
		System.out.println("Msg Received: " + msg.getPayload());
	}

}
