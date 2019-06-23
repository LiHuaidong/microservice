package hdli.junitdemo;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

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

	@Test
	public void testif() {
		int first = 2;
		int second = 2;

		int compareResult = first == second ? 0 : first > second ? 1 : -1;

		compareResult = -1;
		if(compareResult > 0) {
			System.out.println(">");
		} else if(compareResult < 0) {
			System.out.println("<");
		} else if(true) {
			System.out.println("executed true");
		} else {
			System.out.println("executed false");
		}
	}
}
