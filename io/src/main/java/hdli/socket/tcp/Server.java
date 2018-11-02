package hdli.socket.tcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @Description
 * @Author: Lihuaidong
 * @Date: Created at 17:37 2018/11/2
 */
public class Server {

	public static void main(String... args) {
		try {
			ServerSocket serverSocket = new ServerSocket(9066);
			while(true) {
				Socket socket = serverSocket.accept();
				ClientHandler handler = new ClientHandler(socket);
				handler.hand();
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
