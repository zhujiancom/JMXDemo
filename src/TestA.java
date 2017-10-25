import java.util.concurrent.locks.ReentrantLock;

public class TestA {
    final ReentrantLock lock = new ReentrantLock();

    public void test(){
        final ReentrantLock localLock = this.lock;
        localLock.lock();
        System.out.println("hello, local method variable lock.");
        localLock.unlock();
    }
}
