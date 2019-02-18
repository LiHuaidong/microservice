package hdli.sample;

/**
 * @Description
 * @Author: Lihuaidong
 * @Date: Created at 17:13 2018/6/14
 */
public class TestHash {

	public static void main(String... args) {
		String a = "ABC";
		String b = "ABC";

		System.out.println(a.equals(b));

		System.out.println(a.hashCode());
		System.out.println(b.hashCode());
	}

}
