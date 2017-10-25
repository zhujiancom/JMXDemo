import java.util.concurrent.locks.ReentrantLock;

public class TestB {
    final ReentrantLock lock = new ReentrantLock();

    public void test(){
        lock.lock();
        System.out.println("hello class variable lock");
        lock.unlock();
    }
}
