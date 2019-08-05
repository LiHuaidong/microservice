package hdli;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(AppTest.class);
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp() {
        assertTrue(true);
    }

    public void testRateLimit() {
        for (int i = 0; i < 50; i++) {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        if (RedisRatetimeLimit.allow()) {
                            System.out.println(Thread.currentThread().getName() + " can request");
                            Thread.sleep(10000);
                        } else {
                            System.out.println(Thread.currentThread().getName() + " can not request");
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "Thread" + i).start();
        }
    }
}
