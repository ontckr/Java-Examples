package week5sample;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

class ProducerConsumerSYNC_Coll {
	public static void main(String[] args) throws InterruptedException {
		// This is the shared Buffer between producer and consumer.

		List<Integer> sharedBuffer = Collections.synchronizedList(new LinkedList<Integer>());

		Thread producerThread = new Thread(new Producer_S(sharedBuffer, 2), "ProducerThread");
		Thread consumerThread = new Thread(new Consumer_S(sharedBuffer, 2), "ConsumerThread");
		producerThread.start();
		consumerThread.start();
		producerThread.join();
		consumerThread.join();
	}
}

// Producer
class Producer_S implements Runnable {
	List<Integer> sharedBuffer;
	int capacity;

	// Constructor
	Producer_S(List<Integer> sharedBuffer, int capacity) {
		this.sharedBuffer = sharedBuffer;
		this.capacity = capacity;
	}

	public void run() {
		int value = 0;
		while (true) {
			// While condition as mandated to avoid spurious wakeup
			while (sharedBuffer.size() >= capacity) 
				;
			// Putting value in the list
			System.out.println(Thread.currentThread().getName() + ": produced " + value);
			sharedBuffer.add(value);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			value++;
		}
	}
}

// Consumer class
class Consumer_S implements Runnable {
	List<Integer> sharedBuffer;
	int capacity;

	// Constructor
	Consumer_S(List<Integer> sharedBuffer, int capacity) {
		this.sharedBuffer = sharedBuffer;
		this.capacity = capacity;
	}
	
	public void run() {
		while (true) {
			while (sharedBuffer.size() == 0) 
				;

			int value = sharedBuffer.remove(0);
			System.out.println(Thread.currentThread().getName() + ": consumed " + value);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

