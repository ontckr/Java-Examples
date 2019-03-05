package week4sample.RaceSolution;

//This class' shared object will be accessed by threads
class CounterS implements Runnable {
	private int c = 0;

	public void increment() {
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
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
		synchronized (this) {
			// incrementing
			this.increment();
			System.out.println(Thread.currentThread().getName()
					+ ": After increment c = " + this.getValue());
			// decrementing
			this.decrement();
			System.out.println(Thread.currentThread().getName()
					+ " Last value of c = " + this.getValue());
		}
	}
}

public class RaceConditionDemoSolution {
	public static void main(String[] args) {
		CounterS counter = new CounterS();
		Thread t1 = new Thread(counter, "Thread-1");
		Thread t2 = new Thread(counter, "Thread-2");
		Thread t3 = new Thread(counter, "Thread-3");
		t1.start();
		t2.start();
		t3.start();
	}
}