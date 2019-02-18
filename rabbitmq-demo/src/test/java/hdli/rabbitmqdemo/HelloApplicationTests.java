package hdli.rabbitmqdemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Description
 * @Author: Lihuaidong
 * @Date: Created at 16:50 2018/10/25
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RabbitmqDemoApplication.class)
public class HelloApplicationTests {

	@Autowired
	private Sender sender;

	@Test
	public void hello() {
		sender.send();
	}

}
