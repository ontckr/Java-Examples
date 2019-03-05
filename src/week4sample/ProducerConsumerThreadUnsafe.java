package week4sample;

import java.util.LinkedList;

class ProducerConsumerThreadUnsafe {

	public static void main(String[] args) throws InterruptedException {
		// This is the shared Buffer between producer and consumer.
		LinkedList<Integer> sharedBuffer = new LinkedList<Integer>();

		Thread producerThread = new Thread(new Producer1(sharedBuffer, 2), "ProducerThread");
		Thread consumerThread = new Thread(new Consumer1(sharedBuffer, 2), "ConsumerThread");
		producerThread.start();
		consumerThread.start();
		producerThread.join();
		consumerThread.join();
	}
}

// Producer
class Producer1 implements Runnable {
	LinkedList<Integer> sharedBuffer;
	int capacity;

	// Constructor
	Producer1(LinkedList<Integer> sharedBuffer, int capacity) {
		this.sharedBuffer = sharedBuffer;
		this.capacity = capacity;
	}

	public void run() {
		int value = 0;
		while (true) {
			while (sharedBuffer.size() >= capacity)
				;
			// Putting value in the list
			System.out.println(Thread.currentThread().getName() + ": produced " + value);
			sharedBuffer.add(value);
			value++;
		}
	}
}

// Consumer class
class Consumer1 implements Runnable {
	LinkedList<Integer> sharedBuffer;
	int capacity;

	// Constructor
	Consumer1(LinkedList<Integer> sharedBuffer, int capacity) {
		this.sharedBuffer = sharedBuffer;
		this.capacity = capacity;
	}

	public void run() {
		while (true) {
			while (sharedBuffer.size() == 0)
				;
			int value = sharedBuffer.poll();
			System.out.println(Thread.currentThread().getName() + ": consumed " + value);
		}
	}
}
