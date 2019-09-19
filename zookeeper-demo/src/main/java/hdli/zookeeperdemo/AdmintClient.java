package hdli.zookeeperdemo;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class AdmintClient implements Watcher {

    private static final Logger logger = LoggerFactory.getLogger(AdmintClient.class);

    ZooKeeper zk;
    String hostPort;

    public AdmintClient(String hostPort) throws IllegalAccessException, InstantiationException {
        this.hostPort = hostPort;
    }

    void startZK() throws IOException {
        zk = new ZooKeeper("172.16.255.141:2181", 15000, this);
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        logger.info(watchedEvent.toString() + ", " + hostPort);
    }

    public static void main(String[] args) {

    }
}
