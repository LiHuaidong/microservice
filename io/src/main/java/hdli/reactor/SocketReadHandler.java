package hdli.reactor;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * @Description
 * @Author: Lihuaidong
 * @Date: Created at 17:07 2019/10/6
 */
public class SocketReadHandler implements Runnable {

	private SocketChannel socketChannel;

	public SocketReadHandler(Selector selector, SocketChannel socketChannel) throws IOException {
		this.socketChannel = socketChannel;
		socketChannel.configureBlocking(false);

		SelectionKey selectionKey = socketChannel.register(selector, SelectionKey.OP_READ);
		selectionKey.attach(this);

		selectionKey.interestOps(SelectionKey.OP_READ);
		selector.wakeup();
	}

	@Override
	public void run() {
		ByteBuffer inputBuffer = ByteBuffer.allocate(1024);
		inputBuffer.clear();
		try {
			socketChannel.read(inputBuffer);
			//激活线程池 处理这些request
			//requestHandle(new Request(socket,btt));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
