package hdli.exchange;

import java.util.concurrent.Exchanger;

/**
 * @Description
 * @Author: Lihuaidong
 * @Date: Created at 11:40 2020/1/16
 */
public class Main {
	public static void main(String[] args) {
		Exchanger<String> exchanger = new Exchanger();
		Buyer buyer = new Buyer(exchanger);
		Seller seller = new Seller(exchanger);

		new Thread(buyer).start();
		new Thread(seller).start();
	}
}
