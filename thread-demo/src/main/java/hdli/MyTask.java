package hdli;

public class MyTask implements Runnable {
    public void run() {
        int a = 0;
        a += 10;
        System.out.println(Thread.currentThread().getName());
    }
}
