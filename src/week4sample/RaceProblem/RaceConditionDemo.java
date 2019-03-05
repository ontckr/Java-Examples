package week4sample.RaceProblem;

class CounterP  implements Runnable{
    private int c = 0;

    public void increment() {
    	// In order to simulate the real life scenario put a Thread.sleep 
    	// here as many more threads are running and accessing the critical resource. 
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        c++;
    }

    public void decrement() {    
        c--;
    }

    public int getValue() {
        return c;
    }
    
    @Override
    public void run() {
        //incrementing
        this.increment();
        System.out.println(Thread.currentThread().getName() + ": After increment c = " + this.getValue());
        //decrementing
        this.decrement();
        System.out.println(Thread.currentThread().getName() + " Last value of c = " + this.getValue());        
    }
}

public class RaceConditionDemo{
    public static void main(String[] args) {
        CounterP counter = new CounterP();
        Thread t1 = new Thread(counter, "Thread-1");
        Thread t2 = new Thread(counter, "Thread-2");
        Thread t3 = new Thread(counter, "Thread-3");
        t1.start();
        t2.start();
        t3.start();
    }    
}