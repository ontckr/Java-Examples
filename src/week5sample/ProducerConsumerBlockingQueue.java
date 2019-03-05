package week5sample;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

class ProducerConsumerBlockingQueue {
	public static void main(String[] args) throws InterruptedException {
		// This is the shared Buffer between producer and consumer.
		BlockingQueue<Integer> sharedBuffer = new LinkedBlockingQueue<Integer>(2);

		Thread producerThread = new Thread(new Producer(sharedBuffer),
				"ProducerThread");
		Thread consumerThread = new Thread(new Consumer(sharedBuffer),
				"ConsumerThread");
		producerThread.start();
		consumerThread.start();
		producerThread.join();
		consumerThread.join();
	}
}

// Producer
class Producer implements Runnable {
	BlockingQueue<Integer> sharedBuffer;

	// Constructor
	Producer(BlockingQueue<Integer> sharedBuffer) {
		this.sharedBuffer = sharedBuffer;
	}

	public void run() {
		int value = 0;
		// Putting value in the list
		try {
			while (true) {
				sharedBuffer.put(value);
				System.out.println(Thread.currentThread().getName() + ": produced "
						+ value);
				value++;			
			}
		} catch (InterruptedException e) {}
	}
}

// Consumer class
class Consumer implements Runnable {
	BlockingQueue<Integer> sharedBuffer;

	// Constructor
	Consumer(BlockingQueue<Integer> sharedBuffer) { 
		this.sharedBuffer = sharedBuffer;
	}

	public void run() {
		try {
			while (true) {
				int value = sharedBuffer.take();
				System.out.println(Thread.currentThread().getName() + ": consumed "
					+ value);
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {}
	}
}
