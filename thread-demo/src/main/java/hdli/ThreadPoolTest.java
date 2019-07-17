package hdli;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {

    private static int corePoolSize = 3;

    private static int maxPoolSize = 5;

    // 线程存活时间：当线程数量超过corePoolSize时，10秒钟空闲即关闭线程
    private static int keepAliveTime = 10000;

    private static BlockingQueue<Runnable> workQueue = null;

    private static ThreadPoolExecutor threadPoolExecutor = null;

    static {
        workQueue = new LinkedBlockingQueue<Runnable>(5);
        threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveTime, TimeUnit.SECONDS, workQueue);
    }

    public static void main(String[] args) {
        try {
            for (int i = 0; i < 200; i++) {
                System.out.println("=======第" + i + "次");
                threadPoolExecutor.execute(new MyTask());
                System.out.println("线程池中正在执行的线程数量：" + threadPoolExecutor.getPoolSize());
                System.out.println("线程池缓存的任务队列数量：" + threadPoolExecutor.getQueue().size());
            }
        } finally {
            threadPoolExecutor.shutdown();
        }
    }
}
