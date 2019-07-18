package hdli.transferqueue;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TransferQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description
 * @Author: Lihuaidong
 * @Date: Created at 10:26 2019/7/15
 */
public class Producer implements Runnable {

	private static final Logger logger = LoggerFactory.getLogger(Producer.class);

	private final TransferQueue<String> transferQueue;
	private final String name;
	final int numberOfMessagesToProduce;
	final AtomicInteger numberOfProducedMessages = new AtomicInteger();

	Producer(TransferQueue<String> transferQueue, String name, Integer numberOfMessagesToProduce) {
		this.transferQueue = transferQueue;
		this.name = name;
		this.numberOfMessagesToProduce = numberOfMessagesToProduce;
	}

	public void run() {
		try {
			for (int i = 0; i < numberOfMessagesToProduce; i++) {
				if(logger.isDebugEnabled()) {
					logger.debug("Producer: " + name + " is waiting to transfer...");
				}

				boolean added = transferQueue.tryTransfer("A" + i, 4000, TimeUnit.MILLISECONDS);
				if(added) {
					numberOfProducedMessages.incrementAndGet();
					if (logger.isDebugEnabled()) {
						logger.debug("Producer: " + name + " transferred element: A" + i);
					}
				} else {
					logger.debug("can not add an element due to the timeout");
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
