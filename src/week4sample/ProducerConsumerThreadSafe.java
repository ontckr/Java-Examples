package week4sample;

import java.util.LinkedList;

class ProducerConsumerThreadSafe {

	public static void main(String[] args) throws InterruptedException {
		// This is the shared Buffer between producer and consumer.
		LinkedList<Integer> sharedBuffer = new LinkedList<Integer>();
		
		Thread producerThread = new Thread(new Producer(sharedBuffer, 2), "ProducerThread");
		Thread consumerThread = new Thread(new Consumer(sharedBuffer, 2), "ConsumerThread");
		producerThread.start();
		consumerThread.start();
		producerThread.join();
		consumerThread.join();
	}
}

// Producer
class Producer implements Runnable {
	LinkedList<Integer> sharedBuffer;
	int capacity;

	// Constructor
	Producer(LinkedList<Integer> sharedBuffer, int capacity) {
		this.sharedBuffer = sharedBuffer;
		this.capacity = capacity;
	}

	public void run() {
		int value = 0;
		while (true) {
			synchronized (sharedBuffer) {
				// While condition as mandated to avoid spurious wakeup
				while (sharedBuffer.size() >= capacity) {
					try {
						sharedBuffer.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				// Putting value in the list
				System.out.println(Thread.currentThread().getName() + ": produced " + value);
				sharedBuffer.add(value);
				value++;
				sharedBuffer.notify();
			}
		}
	}
}

// Consumer class
class Consumer implements Runnable {
	LinkedList<Integer> sharedBuffer;
	int capacity;

	// Constructor
	Consumer(LinkedList<Integer> sharedBuffer, int capacity) {
		this.sharedBuffer = sharedBuffer;
		this.capacity = capacity;
	}

	public void run() {
		while (true) {
			synchronized (sharedBuffer) {
				while (sharedBuffer.size() == 0) {
					try {
						sharedBuffer.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				int value = sharedBuffer.poll();
				System.out.println(Thread.currentThread().getName() + ": consumed " + value);
				sharedBuffer.notify();
			}
		}
	}
}
