package hdli.lock;

public class Main {

    public static void main(String[] args) {
        PrintQueue printQueue = new PrintQueue();
        for(int i=0; i<10; i++) {
            new Thread(new Job(printQueue), "Thread" + i).start();
        }
    }
}
