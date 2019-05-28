package hdli.socket.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * @Description
 * @Author: Lihuaidong
 * @Date: Created at 18:14 2018/11/2
 */
public class ClientHandler extends Thread {

	private Socket clientSocket;

	public ClientHandler(Socket socket) {
		clientSocket = socket;
	}

	public void hand () {
		if(clientSocket != null) {
			this.start();
		}
	}

	@Override
	public void run() {
		try {
			System.out.println("receive success");
			DataInputStream inputStream = new DataInputStream(clientSocket.getInputStream());
			String msg = inputStream.readUTF();
			System.out.println(msg);

			DataOutputStream outputStream = new DataOutputStream(clientSocket.getOutputStream());
			outputStream.writeUTF("I Love Huaidong");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				clientSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public Socket getClientSocket() {
		return clientSocket;
	}

	public void setClientSocket(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}
}
