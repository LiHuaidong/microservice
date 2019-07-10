package hdli;

import hdli.DataStructures.RedBlackTree;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AppTest extends TestCase {

    public AppTest() {
        RedBlackTree tree = new RedBlackTree(-1000);
        tree.insert(12);
        tree.insert(2);
        tree.insert(3);
        tree.insert(11);
        tree.insert(13);
        tree.insert(15);
        System.out.println("min value = " + tree.findMin());
        System.out.println("max value = " + tree.findMax());
        tree.printTree();
    }

    public static Test suite() {
        return new TestSuite(AppTest.class);
    }

    public void testRedBlackTree() {

    }

}
