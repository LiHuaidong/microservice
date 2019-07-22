package hdli.condition;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Buffer {

    private LinkedList<String> buffer;
    private int maxSize;
    private ReentrantLock lock;
    private Condition hasLines;
    private Condition hasSpace;
    private boolean pendingLines;

    public Buffer(int maxSize) {
        this.maxSize = maxSize;
        buffer = new LinkedList<String>();
        lock = new ReentrantLock();
        hasLines = lock.newCondition();
        hasSpace = lock.newCondition();
        pendingLines = true;
    }

    public void insert(String line) {
        lock.lock();
        try {
            while (buffer.size() == maxSize) {
                hasSpace.await();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public String get() {
        String line = null;
        lock.lock();
        try {
            while (buffer.size() == 0 && hasPendingLines()) {
                hasLines.await();
            }
            if(hasPendingLines()) {
                line = buffer.poll();
                hasSpace.signalAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return line;
    }

    public void setPendingLines(boolean pendingLines) {
        this.pendingLines = pendingLines;
    }

    public boolean hasPendingLines() {
        return pendingLines || buffer.size() > 0;
    }

}
