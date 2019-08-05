package hdli;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.LockSupport;

/**
 * @Description
 * @Author: Lihuaidong
 * @Date: Created at 16:32 2019/7/22
 */
public class LockSupportDemo {

	public static void main(String[] args) {
		final Thread boyThread = new Thread(new Runnable() {
			public void run() {
				System.out.println("boy: 我要吃鸡");
				LockSupport.park();
				System.out.println("boy: park1");
				LockSupport.park(); // 第二次会阻塞住，因为只有一个permit
				System.out.println("boy: park2");
				System.out.println("boy: 开始吃鸡了");
			}
		}, "boyThread");

		final Thread girlThread = new Thread(new Runnable() {
			public void run() {
				try {
					Thread.sleep(4000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("girl: 允许");
				LockSupport.unpark(boyThread); // unpark两次，但是permit不会叠加
				LockSupport.unpark(boyThread);
			}
		}, "girlThread");

		boyThread.start();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		girlThread.start();

	}

}
