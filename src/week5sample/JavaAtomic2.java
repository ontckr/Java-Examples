package week5sample;

import java.util.concurrent.atomic.AtomicInteger;

public class JavaAtomic2 {

    public static void main(String[] args) throws InterruptedException {
    	ProcessingThread2 pt = new ProcessingThread2();
        Thread t1 = new Thread(pt, "t1");
        Thread t2 = new Thread(pt, "t2");
        t1.start();
        t2.start();       
        t1.join();
        t2.join();
        System.out.println("Processing count=" + pt.getCount());
    }
}

class ProcessingThread2 implements Runnable {
    private AtomicInteger count = new AtomicInteger(0);
    public void run() {
    	try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    	count.incrementAndGet();

    }
    public int getCount() { return this.count.get(); }
}