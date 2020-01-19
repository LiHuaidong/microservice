package hdli.semphore;

import java.util.concurrent.Semaphore;

public class NumberTask implements Runnable {

    private Semaphore semaphore;

    private int currentIndex = 0;

    private int[] inner = new int[52];

    public NumberTask(Semaphore semaphore) {
        this.semaphore = semaphore;
        for (int i = 0; i < 52; i++) {
            inner[i] = i;
        }
    }

    public void run() {
        while(currentIndex<52){
            try {
                print();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void print() throws InterruptedException {
        semaphore.acquire();
        Thread.currentThread().sleep(100);
        System.out.print(inner[currentIndex] + "" + inner[currentIndex + 1] + "");
        currentIndex += 2;
        semaphore.release();
    }
}