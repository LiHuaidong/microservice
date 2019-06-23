package hdli.junitdemo;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @Description
 * @Author: Lihuaidong
 * @Date: Created at 16:42 2019/5/30
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
		TestJunit1.class,
		TestJunit2.class
})
public class JunitTestSuite  {
}
