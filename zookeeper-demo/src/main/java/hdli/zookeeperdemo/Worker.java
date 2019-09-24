package hdli.zookeeperdemo;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Random;

public class Worker implements Watcher  {

    private static final Logger logger = LoggerFactory.getLogger(Worker.class);

    ZooKeeper zk;
    String status;
    String hostPort;
    String serverId = Integer.toHexString(Random.class.newInstance().nextInt());

    public Worker(String hostPort) throws IllegalAccessException, InstantiationException {
    }

    void startZK() throws IOException {
        zk = new ZooKeeper("172.16.255.141:2181", 15000, this);
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        logger.info(watchedEvent.toString() + ", " + hostPort);
    }

    void register() {
        zk.create("/workers/worker" + serverId, "Idle".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL, createWorkercallBack, null);
    }

    AsyncCallback.StringCallback createWorkercallBack = new AsyncCallback.StringCallback() {
        @Override
        public void processResult(int i, String s, Object o, String s1) {
            switch (KeeperException.Code.get(i)){
                case CONNECTIONLOSS:
                    register();
                    break;
                case OK:
                    logger.info("Registered successfully: " + serverId);
                    break;
                case NODEEXISTS:
                    logger.warn("Already registered: " + serverId);
                    break;
                default:
                    logger.error("Something went wrong: " + KeeperException.create(KeeperException.Code.get(i), s));
            }
        }
    };

    public static void main(String[] args) {
        try {
            Worker w = new Worker("2181");
            w.startZK();

            w.register();
            Thread.sleep(30000);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    AsyncCallback.StatCallback statusUpdateCallback = new AsyncCallback.StatCallback() {
        @Override
        public void processResult(int rc, String path, Object ctx, Stat stat) {
            switch (KeeperException.Code.get(rc)) {
                case CONNECTIONLOSS:
                    updateStatus((String) ctx);
                    return;
            }
        }
    };

    synchronized private void updateStatus(String status) {
        if (status == this.status) {
            zk.setData("/workers/work" + serverId, status.getBytes(), -1, statusUpdateCallback, status);
        }
    }

    public void setStatus(String status) {
        this.status = status;
        updateStatus(status);
    }
}
