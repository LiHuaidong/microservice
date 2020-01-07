package hdli.test2;

public class Test2 {

    public static void main(String[] args) {
        Integer condition1 = 1;
        Integer condition2 = 1;
        LetterTask letterTask = new LetterTask(condition1, condition2);
        NumberTask numberTask = new NumberTask(condition1, condition2);

        Thread letterThread = new Thread(letterTask);
        Thread numberThread = new Thread(numberTask);

        numberThread.start();
        letterThread.start();
    }

}
