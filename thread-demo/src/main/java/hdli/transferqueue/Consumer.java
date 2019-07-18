package hdli.transferqueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description
 * @Author: Lihuaidong
 * @Date: Created at 10:27 2019/7/15
 */
public class Consumer implements Runnable {

	private final LinkedTransferQueue<String> transferQueue;
	private final String name;
	final int numberOfMessagesToConsume;
	final AtomicInteger numberOfConsumedMessages = new AtomicInteger();

	Consumer(LinkedTransferQueue<String> transferQueue, String name, int numberOfMessagesToConsume) {
		this.transferQueue = transferQueue;
		this.name = name;
		this.numberOfMessagesToConsume = numberOfMessagesToConsume;
	}

	public void run() {
		for (int i = 0; i < numberOfMessagesToConsume; i++) {
			try {
				System.out.println("Consumer: " + name + " is waiting to take element...");
				String element = transferQueue.take();
				longProcessing(element);
				System.out.println("Consumer: " + name + " received element: " + element);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void longProcessing(String element) throws InterruptedException {
		numberOfConsumedMessages.incrementAndGet();
		Thread.sleep(500);
	}
}
