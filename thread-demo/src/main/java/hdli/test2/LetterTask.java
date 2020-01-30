package hdli.test2;

public class LetterTask implements Runnable {

    private Integer tip1;

    private Integer tip2;

    private int currentIndex = 0;

    public char[] inner = new char[26];

    public LetterTask(Integer condition1, Integer condition2) {
        tip1 = condition1;
        tip2 = condition2;
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
        synchronized (tip2) {
            System.out.print(inner[currentIndex]+" ");
            currentIndex++;
            tip1.notifyAll();
            tip2.wait();
        }

    }
}
