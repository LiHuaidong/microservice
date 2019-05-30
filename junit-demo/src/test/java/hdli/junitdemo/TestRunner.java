package hdli.junitdemo;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.util.List;

/**
 * @Description
 * @Author: Lihuaidong
 * @Date: Created at 15:07 2019/5/30
 */
public class TestRunner {

	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(JunitTestSuite.class);
		List<Failure> failureList = result.getFailures();
		for(Failure failure : failureList) {
			System.out.println(failure.toString());
		}
		System.out.println(result.wasSuccessful());
	}

}
