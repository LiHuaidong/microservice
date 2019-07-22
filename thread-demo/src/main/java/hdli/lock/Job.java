package hdli.lock;

public class Job implements Runnable {

    private PrintQueue printQueue;

    public Job(PrintQueue printQueue) {
        this.printQueue = printQueue;
    }

    public void run() {
        System.out.println(Thread.currentThread().getName() + " Going to print a document");
        printQueue.printJob(new Object());
        System.out.println(Thread.currentThread().getName() + " The document has been printed");
    }

}
