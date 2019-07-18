package hdli.transferqueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.concurrent.TransferQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description
 * @Author: Lihuaidong
 * @Date: Created at 10:27 2019/7/15
 */
public class Consumer implements Runnable {
	private static final Logger logger = LoggerFactory.getLogger(Consumer.class);

	private final TransferQueue<String> transferQueue;
	private final String name;
	final int numberOfMessagesToConsume;
	final AtomicInteger numberOfConsumedMessages = new AtomicInteger();

	Consumer(TransferQueue<String> transferQueue, String name, int numberOfMessagesToConsume) {
		this.transferQueue = transferQueue;
		this.name = name;
		this.numberOfMessagesToConsume = numberOfMessagesToConsume;
	}

	public void run() {
		for (int i = 0; i < numberOfMessagesToConsume; i++) {
			try {
				logger.debug("Consumer: " + name + " is waiting to take element...");
				String element = transferQueue.take();
				longProcessing(element);
				logger.debug("Consumer: " + name + " received element: " + element);
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
