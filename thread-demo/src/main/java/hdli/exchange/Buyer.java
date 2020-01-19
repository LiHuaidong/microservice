package hdli.exchange;

import java.util.concurrent.Exchanger;

/**
 * @Description
 * @Author: Lihuaidong
 * @Date: Created at 11:40 2020/1/16
 */
public class Buyer implements Runnable {

	private Exchanger<String> exchanger;

	private int[] inner = new int[52];

	public Buyer(Exchanger<String> exchanger) {
		this.exchanger = exchanger;
		for (int i = 0; i < 52; i++) {
			inner[i] = i;
		}
	}

	public void run() {
		try {
			for (int i = 0; i < 26; i++) {
				String current = "" + inner[i] + inner[i + 1];
				String other = exchanger.exchange(current);
				System.out.print(other);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
