package hdli.junitdemo;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @Description
 * @Author: Lihuaidong
 * @Date: Created at 16:41 2019/5/30
 */
public class TestJunit2 {

	String message = "Robert";
	MessageUtil messageUtil = new MessageUtil(message);

	@Test public void testSalutationMessage() {
		System.out.println("Inside testSalutationMessage()");
		message = "Hi!" + "Robert";
		assertEquals(message, messageUtil.salutationMessage());
	}
}
