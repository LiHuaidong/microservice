package hdli.test2;

public class NumberTask implements Runnable {

    private Integer tip1;

    private Integer tip2;

    private int currentIndex = 0;

    private int[] inner = new int[52];

    public NumberTask(Integer condition1, Integer condition2) {
        tip1 = condition1;
        tip2 = condition2;
        for (int i = 0; i < 52; i++) {
            inner[i] = i;
        }
    }

    public void run() {
        while(currentIndex<52){
            try {
//                if(tip > 0){
//                    print();
//                }
                print();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void print() throws InterruptedException {
        synchronized (tip1) {
            System.out.print(inner[currentIndex] + "" + inner[currentIndex + 1] + "");
            currentIndex += 2;
//            tip = 0;
            tip1.wait();
            tip2.notifyAll();
        }
    }
}