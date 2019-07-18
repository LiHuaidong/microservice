package hdli.creation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author: Lihuaidong
 * @Date: Created at 17:47 2019/7/1
 */
public class Singleton implements Comparable<Integer> {

//	private volatile static Singleton singleton;
//	private Singleton (){}
//	public static Singleton getSingleton() {
//		if (singleton == null) {
//			synchronized (Singleton.class) {
//				if (singleton == null) {
//					singleton = new Singleton();
//				}
//			}
//		}
//		return singleton;
//	}

	private static final String name = "abc";
	public static volatile int a;

	public synchronized void resetA(int b) {
		this.a = b;
	}

	public static void main(String[] args) {
		Singleton singleton = new Singleton();
		singleton.resetA(12);

		List<Integer> testMap = new ArrayList<>();
		testMap.add(1);
		testMap.add(1);
		testMap.add(1);
		testMap.add(1);
		testMap.add(1);
		testMap.add(1);
		testMap.add(1);

		singleton.a = 67;

		for (int i = 0; i < 10; i++) {
			Singleton tmp = new Singleton();
			singleton.resetA(12);
			tmp.a = i;
		}

		Singleton tmp1;
		for (int i = 0; i < 10; i++) {
			tmp1 = new Singleton();
			singleton.resetA(12);
			tmp1.a = i;
		}

		try {
			testMap.parallelStream().collect(Collectors.toSet());
		} catch (Exception e) {
			System.out.println("args = [" + args + "]");
		} finally {
			System.out.println("args = [" + args + "]");
		}


	}

	@Override public int compareTo(Integer o) {
		return 0;
	}
}
