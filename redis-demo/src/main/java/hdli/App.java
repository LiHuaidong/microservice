package hdli;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        for (int i = 0; i < 50; i++) {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        if (RedisRatetimeLimit.allow()) {
                            System.out.println(Thread.currentThread().getName() + " can request");
                            Thread.sleep(10000);
                        } else {
                            System.out.println(Thread.currentThread().getName() + " can not request");
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "Thread" + i).start();
        }
    }
}
