package hdli.streamprovider;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author: Lihuaidong
 * @Date: Created at 17:14 2019/5/27
 */
@Component
public interface CustomSink {

	String INPUT = "input3";

	String INPUT0 = "input0";

	String INPUT1 = "input1";

	String INPUT2 = "input2";

	@Input(INPUT)
	SubscribableChannel input();

	@Input(INPUT0)
	SubscribableChannel input0();

	@Input(INPUT1)
	SubscribableChannel input1();

	@Input(INPUT2)
	SubscribableChannel input2();


}
