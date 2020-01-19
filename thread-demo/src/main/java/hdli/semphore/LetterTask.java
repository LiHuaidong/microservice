package hdli.semphore;

import java.util.concurrent.Semaphore;

public class LetterTask implements Runnable {

    private Semaphore semaphore;

    private int currentIndex = 0;

    public char[] inner = new char[26];

    public LetterTask(Semaphore semaphore) {
        this.semaphore = semaphore;
        for (int i = 0; i < 26; i++) {
            inner[i] = (char) (i + 65);
        }
    }

    public void run() {
        while(currentIndex<26) {
            try {
                print();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void print() throws InterruptedException {
        semaphore.acquire();
        System.out.print(inner[currentIndex] + " ");
        currentIndex++;
        semaphore.release();
    }
}
