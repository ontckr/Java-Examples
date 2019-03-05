package week5sample;

public class JavaNonAtomic {
    public static void main(String[] args) throws InterruptedException {
        ProcessingThread pt = new ProcessingThread();
        Thread t1 = new Thread(pt, "t1");
        Thread t2 = new Thread(pt, "t2");
        t1.start();
        t2.start();       
        t1.join();
        t2.join();
        System.out.println("Processing count=" + pt.getCount());
    }
}

class ProcessingThread implements Runnable {
    private int count;
    public void run() {
        	try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
            count++;
    }
    public int getCount() { return this.count; }
}
