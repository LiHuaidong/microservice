package hdli.junitdemo;

/**
 * @Description
 * @Author: Lihuaidong
 * @Date: Created at 16:36 2019/5/30
 */
public class MessageUtil {

	private String message;

	public MessageUtil(String message){
		this.message = message;
	}

	public String printMessage(){
		System.out.println(message);
		return message;
	}

	public void printMessage1(){
		System.out.println(message);
		while(true);
	}

	public void printMessage2(){
		System.out.println(message);
		int a =0;
		int b = 1/a;
	}

	public String salutationMessage(){
		message = "Hi!" + message;
		System.out.println(message);
		return message;
	}

}
