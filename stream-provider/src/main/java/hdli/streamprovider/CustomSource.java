package hdli.streamprovider;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author: Lihuaidong
 * @Date: Created at 18:09 2019/5/27
 */
@Component
public interface CustomSource {

	String OUTPUT = "output3";

	String OUTPUT1 = "output1";

	String OUTPUT2 = "output2";

	@Output(OUTPUT)
	MessageChannel output();

	@Output(OUTPUT1)
	MessageChannel output1();

	@Output(OUTPUT2)
	MessageChannel output2();

}
