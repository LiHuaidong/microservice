package hdli.zookeeperdemo;

import org.apache.zookeeper.*;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.proto.WatcherEvent;

import java.io.IOException;
import java.util.Random;

import static org.apache.zookeeper.ZooDefs.Ids.OPEN_ACL_UNSAFE;

public class Master implements Watcher{

    ZooKeeper zk;
    String hostPort;
    String serverId = Integer.toHexString(Random.class.newInstance().nextInt());

    public Master(String hostPort) throws IllegalAccessException, InstantiationException {
        this.hostPort = hostPort;
    }

    void startZK() throws IOException {
        zk = new ZooKeeper("172.16.255.141:2181", 15000, this);
    }

    public static void main(String[] args) throws Exception {
        Master m = new Master("2181");
        m.startZK();

        Thread.sleep(60000);

        m.startZK();
    }

    void stopZK() throws InterruptedException {
        zk.close();
    }

    void runForMaster() throws KeeperException, InterruptedException {
        zk.create("master", serverId.getBytes(), OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
    }

    @Override
    public void process(WatchedEvent e) {
        System.out.println("e = [" + e + "]");
    }
}
