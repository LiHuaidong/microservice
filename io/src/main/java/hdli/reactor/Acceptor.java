package hdli.reactor;

import java.io.IOException;
import java.nio.channels.SocketChannel;

/**
 * @Description
 * @Author: Lihuaidong
 * @Date: Created at 17:07 2019/10/6
 */
public class Acceptor implements Runnable {

	private Reactor reactor;

	public Acceptor(Reactor reactor) {
		this.reactor = reactor;
	}

	@Override
	public void run() {
		try {
			SocketChannel socketChannel = reactor.serverSocketChannel.accept();
			if(socketChannel != null) {
				new SocketReadHandler(reactor.selector, socketChannel);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
