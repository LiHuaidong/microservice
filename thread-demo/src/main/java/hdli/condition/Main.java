package hdli.condition;

public class Main {

    public static void main(String[] args) {
        FileMock mock = new FileMock(100, 10);
        Buffer buffer = new Buffer(20);

        for (int i = 0; i < 3; i++) {
            new Thread(new Consumer(buffer), "Consumer" + i).start();
        }
        new Thread(new Producer(mock, buffer)).start();
    }

}
