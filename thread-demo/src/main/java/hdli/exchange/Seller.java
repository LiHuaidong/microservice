package hdli.exchange;

import java.util.concurrent.Exchanger;

/**
 * @Description
 * @Author: Lihuaidong
 * @Date: Created at 11:40 2020/1/16
 */
public class Seller implements Runnable {

	private Exchanger<String> exchanger;

	public char[] inner = new char[26];

	public Seller(Exchanger<String> exchanger) {
		this.exchanger = exchanger;
		for (int i = 0; i < 26; i++) {
			inner[i] = (char) (i + 65);
		}
	}

	public void run() {
		try {
			for (int i = 0; i < 26; i++) {
				String current = "" + inner[i];
				String other = exchanger.exchange(current);
				System.out.print(other);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
