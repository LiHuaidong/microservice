package hdli.zookeeperdemo;

import org.apache.zookeeper.*;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.data.Stat;
import org.apache.zookeeper.proto.WatcherEvent;

import java.io.IOException;
import java.util.Random;

import static org.apache.zookeeper.ZooDefs.Ids.OPEN_ACL_UNSAFE;

public class Master implements Watcher{

    ZooKeeper zk;
    String hostPort;
    String serverId = Integer.toHexString(Random.class.newInstance().nextInt());
    boolean isLeader = false;

    public Master(String hostPort) throws IllegalAccessException, InstantiationException {
        this.hostPort = hostPort;
    }

    void startZK() throws IOException {
        zk = new ZooKeeper("192.168.11.132:2181", 15000, this);
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

    boolean checkMaster() {
        try {
            while (true) {
                Stat stat = new Stat();
                byte data[] = zk.getData("/master", false, stat);
                isLeader = new String(data).equals(serverId);
            }
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return isLeader;
    }

    void runForMaster() {
        while (true) {
            try {
                zk.create("/master", serverId.getBytes(), OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
                isLeader = true;
                break;
            } catch (KeeperException e) {
                e.printStackTrace();
                isLeader = false;
                break;
            } catch (InterruptedException e) {

            }
            if (checkMaster()) {
                break;
            }
        }
    }

    @Override
    public void process(WatchedEvent e) {
        System.out.println("e = [" + e + "]");
    }
}
