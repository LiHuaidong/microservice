package hdli.sample;

/**
 * @Description
 * @Author: Lihuaidong
 * @Date: Created at 15:19 2018/5/31
 */
public class MapSample {
	public static void main(String... args) {
//		Map map = new HashMap<String, Object>();
		String test = "http://localhost:8080/mobilemicroservice/leguer/login.shtml";

		int index = test.indexOf("mobilemicroservice");
		String serviceUrl = test.substring(test.indexOf("mobilemicroservice"), test.length()).replace("mobilemicroservice", "");
		System.out.println("serviceUrl = [" + serviceUrl + "]");
	}
}
