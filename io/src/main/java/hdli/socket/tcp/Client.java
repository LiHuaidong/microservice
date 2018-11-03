package hdli.socket.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

/**
 * @Description
 * @Author: Lihuaidong
 * @Date: Created at 16:43 2018/11/3
 */
public class Client {

	public static final String IP_ADDR = "localhost";

	public static final int PORT = 9066;

	public static void main(String... args) {
		Socket socket = null;
		try {
			socket = new Socket(IP_ADDR, PORT);
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			out.writeUTF("I Love Lina");

			DataInputStream input = new DataInputStream(socket.getInputStream());
			String returnMsg = input.readUTF();
			System.out.println(returnMsg);

			Thread.sleep(2000);

			out.close();
			input.close();
		} catch (Exception e) {
		} finally {
			if (socket != null) {
				try {
					socket.close();
				} catch (Exception e) {
				}
			}
		}
	}

}
