package hdli.semphore;

import java.util.concurrent.Semaphore;

/**
 * @Description
 * @Author: Lihuaidong
 * @Date: Created at 14:09 2020/1/7
 */
public class Main {

	public static void main(String[] args) {
		Semaphore semaphore = new Semaphore(1);

		LetterTask letterTask = new LetterTask(semaphore);
		NumberTask numberTask = new NumberTask(semaphore);

		Thread letterThread = new Thread(letterTask);
		Thread numberThread = new Thread(numberTask);

		numberThread.start();
		letterThread.start();
	}
}
