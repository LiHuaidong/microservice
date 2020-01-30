package hdli.reactor;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @Description
 * @Author: Lihuaidong
 * @Date: Created at 17:27 2019/10/6
 */
public class Client{

	SocketChannel socketChannel;
	public Client(int remotePort) throws IOException {
		InetSocketAddress socketAddress = new InetSocketAddress(InetAddress.getLocalHost(), remotePort);
		socketChannel = SocketChannel.open(socketAddress);
	}

	public void send(String msg) throws IOException {
		socketChannel.write(ByteBuffer.wrap(msg.getBytes()));
	}
}
