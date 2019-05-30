package hdli.junitdemo;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @Description
 * @Author: Lihuaidong
 * @Date: Created at 15:17 2019/5/30
 */
public class TestAssertions {

	@Test
	public void testAssertions() {
		String str1 = new String("abc");
		String str2 = new String("abc");
		String str3 = null;
		String str4 = "abc";
		String str5 = "abc";
		int val1 = 5;
		int val2 = 6;
		String[] expectedArray = { "one", "two", "three" };
		String[] resultArray = { "one", "two", "three" };

		assertEquals(str1, str2);
		assertTrue(val1 < val2);
		assertFalse(val1 > val2);
		assertNotNull(str1);
		assertNull(str3);
		assertSame(str4, str5);
		assertNotSame(str1, str2);
		assertArrayEquals(expectedArray, resultArray);
	}

}
