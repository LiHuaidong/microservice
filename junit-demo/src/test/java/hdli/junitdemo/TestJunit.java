package hdli.junitdemo;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * @Description
 * @Author: Lihuaidong
 * @Date: Created at 16:37 2019/5/30
 */
public class TestJunit {

	String message = "Hello World";
	MessageUtil messageUtil = new MessageUtil(message);

	@Test
	public void testPrintMessage() {
		assertEquals(message,messageUtil.printMessage());
	}
}
