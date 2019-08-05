package hdli.synchronousqueue;

import java.util.concurrent.SynchronousQueue;

public class SynchronousQueueDemo {

    public static void main(String[] args) {
        final SynchronousQueue<String> queue = new SynchronousQueue<String>();
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                public void run() {

                }
            }).start();
        }
    }

}
