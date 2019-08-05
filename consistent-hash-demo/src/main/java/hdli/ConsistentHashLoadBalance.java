package hdli;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.TreeMap;

public class ConsistentHashLoadBalance {

    private TreeMap<Long, String> virtualNodes = new TreeMap<>();
    private LinkedList<String> nodes;
    private int replicCnt;

    public ConsistentHashLoadBalance(LinkedList<String> nodes, int replicCnt) {
        this.nodes = nodes;
        this.replicCnt = replicCnt;
        initalization();
    }

    private void initalization() {
        for(String nodeName : nodes) {
            for(int i=0; i<replicCnt/4; i++) {
                String virtualNodeName = getNodeNameByIndex(nodeName, i);
                for(int j=0; j<4; j++) {
                    virtualNodes.put(hash(virtualNodeName, j), nodeName);
                }
            }
        }
    }

    private String getNodeNameByIndex(String nodeName, int index) {
        return new StringBuffer(nodeName).append("&&").append(index).toString();
    }

    private Long hash(String nodeName, int number) {
        byte[] digest = md5(nodeName);
        return null;
    }

    public byte[] md5(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.reset();
            md.update(str.getBytes("UTF-8"));
            return md.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
