package hdli.reactor;

import lombok.Getter;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @Description
 * @Author: Lihuaidong
 * @Date: Created at 16:56 2019/10/6
 */
@Getter
public class Reactor implements Runnable {

	protected final Selector selector;
	protected final ServerSocketChannel serverSocketChannel;

	public Reactor(int port) throws IOException {
		selector = Selector.open();
		serverSocketChannel = ServerSocketChannel.open();
		InetSocketAddress inetSocketAddress = new InetSocketAddress(InetAddress.getLocalHost(), port);
		serverSocketChannel.bind(inetSocketAddress);
		serverSocketChannel.configureBlocking(false);

		SelectionKey selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
		selectionKey.attach(new Acceptor(this));
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				selector.select();
				Set<SelectionKey> selectionKeys = selector.selectedKeys();
				Iterator<SelectionKey> it = selectionKeys.iterator();
				while (it.hasNext()) {
					SelectionKey selectionKey = it.next();
					if (selectionKey.isAcceptable()) {
						dispatch(selectionKey);
					}
					selectionKeys.clear();
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	void dispatch(SelectionKey key) {
		Runnable r = (Runnable) (key.attachment());
		if (r != null) {
			Thread thread = new Thread(r);
			thread.start();
		}
	}
}
