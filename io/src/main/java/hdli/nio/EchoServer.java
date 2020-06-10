package hdli.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * @Description
 * @Author: Lihuaidong
 * @Date: Created at 15:19 2020/6/10
 */
public class EchoServer {

	private Selector selector;

	private ByteBuffer buffer = ByteBuffer.allocateDirect (1024);

	public static void main(String[] args) {
		EchoServer server = new EchoServer();
		server.init();
		server.startWork();
	}

	public void init() {
		try {
			ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
			selector = Selector.open();
			ServerSocket serverSocket = serverSocketChannel.socket();
			serverSocket.bind(new InetSocketAddress(8668));

			serverSocketChannel.configureBlocking(false);

			serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void startWork() {
		try {
			while (true) {
				int n = selector.select();
				if (n == 0) {
					continue;
				}

				Iterator<SelectionKey> it = selector.selectedKeys().iterator();
				while (it.hasNext()) {
					SelectionKey key = it.next();
					if (key.isAcceptable()) {
						ServerSocketChannel server = (ServerSocketChannel) key.channel();
						SocketChannel channel = server.accept();
						registerChannel(selector, channel, SelectionKey.OP_READ);
						doWork(channel);
					}
					if (key.isReadable()) {
						processData(key);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void registerChannel(Selector selector, SelectableChannel channel, int ops) {
		if(channel == null) {
			return;
		}

		try {
			channel.configureBlocking(false);
			channel.register(selector, ops);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doWork(SocketChannel channel) {
		buffer.clear();
		buffer.put(("Hello, I am working, please input some thing, and i will echo to you!\r\n"
				+ "[echo]\r\n"
				+ "___FCKpd___").getBytes());
		buffer.flip();   // buffer存储模型
		try {
			channel.write(buffer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void processData(SelectionKey key) {
		SocketChannel socketChannel = (SocketChannel) key.channel();
		int count;

		buffer.clear(); // 清空buffer
		try{
			// 读取所有的数据
			while ((count = socketChannel.read(buffer)) > 0) {
				buffer.flip();

				while (buffer.hasRemaining()) {
					//如果收到回车键，则在返回的字符前增加[echo]$字样
					if (buffer.get() == (char) 13) {
						buffer.clear();
						buffer.put("[echo]___FCKpd___".getBytes());
						buffer.flip();

					}
					socketChannel.write(buffer);//在Socket里写数据
				}
				buffer.clear(); // 清空buffer
			}

			if (count < 0) {
				// count<0，说明已经读取完毕
				socketChannel.close();
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

}
