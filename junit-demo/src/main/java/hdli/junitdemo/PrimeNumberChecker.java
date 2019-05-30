package hdli.junitdemo;

/**
 * @Description
 * @Author: Lihuaidong
 * @Date: Created at 17:33 2019/5/30
 */
public class PrimeNumberChecker {

	public Boolean validate(final Integer primeNumber) {
		for (int i = 2; i < (primeNumber / 2); i++) {
			if (primeNumber % i == 0) {
				return false;
			}
		}
		return true;
	}
}
