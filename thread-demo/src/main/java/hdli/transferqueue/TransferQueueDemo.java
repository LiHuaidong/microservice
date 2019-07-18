package hdli.transferqueue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedTransferQueue;

/**
 * @Description LinkedTransferQueue 生产者会阻塞到所添加的元素被消费者消费为止
 * @Author: Lihuaidong
 * @Date: Created at 10:26 2019/7/15
 */
public class TransferQueueDemo {

    public static void main(String[] args) {
        ExecutorService exService = Executors.newFixedThreadPool(2);

        LinkedTransferQueue<String> transferQueue = new LinkedTransferQueue<String>();
        Producer producer = new Producer(transferQueue, "producer", 10);
        Consumer consumer = new Consumer(transferQueue, "consumer", 10);

        exService.execute(producer);
        exService.execute(consumer);

        exService.shutdown();
    }
}
