package hdli.zookeeperdemo;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Random;

import static org.apache.zookeeper.ZooDefs.Ids.OPEN_ACL_UNSAFE;

public class Master implements Watcher {

    ZooKeeper zk;
    String hostPort;
    String serverId = Integer.toHexString(Random.class.newInstance().nextInt());
    boolean isLeader = false;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public Master(String hostPort) throws IllegalAccessException, InstantiationException {
        this.hostPort = hostPort;
    }

    void startZK() throws IOException {
        zk = new ZooKeeper("192.168.11.132:2181", 15000, this);
    }

    public static void main(String[] args) throws Exception {
        Master m = new Master("2181");
        m.startZK();
        m.runForMaster();
        m.bootstrap();
        if (m.isLeader) {
            Thread.sleep(60000);
        } else {
            System.out.println("Someone else if the leader");
        }
        m.stopZK();
    }

    void stopZK() throws InterruptedException {
        zk.close();
    }

    void bootstrap() {
        createParent("/workers", new byte[0]);
        createParent("/assign", new byte[0]);
        createParent("/tasks", new byte[0]);
        createParent("/status", new byte[0]);
    }

    void createParent(String path, byte[] data) {
        zk.create(path, data, OPEN_ACL_UNSAFE, CreateMode.PERSISTENT, createParentCallback, data);
    }


    void checkMaster() {
        zk.getData("/master", false, masterCheckCallback, null);
    }

    void runForMaster() {
        zk.create("/master", serverId.getBytes(), OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL, masterCreateCallback, null);
    }

    AsyncCallback.StringCallback masterCreateCallback = new AsyncCallback.StringCallback() {
        @Override
        public void processResult(int i, String s, Object o, String s1) {
            switch (KeeperException.Code.get(i)) {
                case CONNECTIONLOSS:
                    checkMaster();
                    return;
                case OK:
                    isLeader = true;
                    break;
                default:
                    isLeader = false;
            }
            System.out.println("I'm " + (isLeader ? "" : "not") + "the leader");
        }
    };

    AsyncCallback.DataCallback masterCheckCallback = new AsyncCallback.DataCallback() {
        @Override
        public void processResult(int rc, String path, Object ctx, byte[] data, Stat stat) {
            switch (KeeperException.Code.get(rc)) {
                case CONNECTIONLOSS:
                    checkMaster();
                    return;
                case NONODE:
                    runForMaster();
                    return;
            }
        }
    };

    AsyncCallback.StringCallback createParentCallback = new AsyncCallback.StringCallback() {
        @Override
        public void processResult(int rc, String path, Object ctx, String name) {
            switch (KeeperException.Code.get(rc)) {
                case CONNECTIONLOSS:
                    createParent(path, (byte[]) ctx);
                    break;
                case OK:
                    logger.info("Parent created");
                    break;
                case NODEEXISTS:
                    logger.warn("Parent already registered: " + path);
                    break;
                default:
                    logger.error("Something went wrong: ", KeeperException.create(KeeperException.Code.get(rc), path));
            }
        }
    };

    @Override
    public void process(WatchedEvent e) {
        System.out.println("e = [" + e + "]");
    }
}
