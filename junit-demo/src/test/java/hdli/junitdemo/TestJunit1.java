package hdli.junitdemo;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @Description
 * @Author: Lihuaidong
 * @Date: Created at 16:37 2019/5/30
 */
public class TestJunit1 {

	String message = "Robert";
	MessageUtil messageUtil = new MessageUtil(message);

	@Test
	public void testPrintMessage() {
		System.out.println("Inside testPrintMessage()");
		assertEquals(message, messageUtil.printMessage());
	}

	@Test(timeout=1000)
	public void testPrintMessage1() {
		System.out.println("Inside testPrintMessage1()");
		messageUtil.printMessage1();
	}

	@Test(expected = NullPointerException.class)
	public void testPrintMessage2() {
		System.out.println("Inside testPrintMessage1()");
		messageUtil.printMessage2();
	}
}
