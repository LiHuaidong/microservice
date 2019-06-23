package hdli.junitdemo;

import org.junit.*;

/**
 * @Description
 * @Author: Lihuaidong
 * @Date: Created at 15:47 2019/5/30
 */
public class JunitAnnotation {

	static {
		System.out.println("static");
	}

	@BeforeClass
	public static void beforeClass() {
		System.out.println("in before class");
	}

	@AfterClass
	public static void afterClass() {
		System.out.println("in after class");
	}

	@Before
	public void before() {
		System.out.println("in before");
	}

	@After
	public void after() {
		System.out.println("in after");
	}

	@Test
	public void test() {
		System.out.println("in test");
	}

	@Test
	public void testCase1() {
		System.out.println("in test case 1");
	}

	@Test
	public void testCase2() {
		System.out.println("in test case 2");
	}

	@Ignore
	public void ignoreTest() {
		System.out.println("in ignore test");
	}

}
