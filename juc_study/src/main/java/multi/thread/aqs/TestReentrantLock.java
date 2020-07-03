package multi.thread.aqs;
import	java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName TestReentrantLock
 * @Description TODO
 * @Author liangxp
 * @Date 2020/6/27 10:38
 **/
public class TestReentrantLock {
    private static volatile int i = 0;

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();

        lock.lock();

        i++;

        lock.unlock();
    }
}
