package week5sample;
import java.util.LinkedList;  
import java.util.concurrent.locks.Condition; 
import java.util.concurrent.locks.Lock; 
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumerLock { 
	public static void main (String[] args) throws InterruptedException { 
		// This is the shared Buffer between producer and consumer. 
		Buffer sharedBuffer = new Buffer(2); 
		
		Thread producerThread = new Thread(new Producer2(sharedBuffer), "ProducerThread");
		Thread consumerThread = new Thread(new Consumer2(sharedBuffer), "ConsumerThread");
		
		// starting producer and consumer threads 
		producerThread.start();
		consumerThread.start();
		producerThread.join();
		consumerThread.join();	
	} 
} 
class Buffer { 
	
	// lock and condition variables 
	private final Lock lock = new ReentrantLock(); 
	private final Condition bufferNotFull = lock.newCondition(); 
	private final Condition bufferNotEmpty = lock.newCondition(); 
	
	// Internal Buffer
	private final LinkedList<Integer> sharedBuffer = new LinkedList<Integer>();
	int value = 0;
	private  int capacity;

	Buffer (int capacity) {
		this.capacity = capacity;
	}
	public void put()  { 
		lock.lock(); 
		try { 
			while (sharedBuffer.size() == capacity) { 
				System.out.println(Thread.currentThread().getName() + " : Buffer is full, waiting"); 
				bufferNotEmpty.await(); 
			}  
			sharedBuffer.add(value);
			System.out.println(Thread.currentThread().getName() + ": produced " + value);
			// signal consumer thread that, buffer has element now 
			System.out.println(Thread.currentThread().getName() + 
						" : Signalling that buffer is no more empty now"); 
			value++;
			bufferNotFull.signalAll(); 
		} catch (InterruptedException e) {
			e.printStackTrace();
		}  
		finally { 
			lock.unlock(); 
		} 
	} 
	public void get()  { 
		lock.lock(); 
		try { 
			while (sharedBuffer.size() == 0) { 
				System.out.println(Thread.currentThread().getName() + 
						" : Buffer is empty, waiting"); 
				bufferNotFull.await(); 
			} 
			int value = sharedBuffer.poll();
			System.out.println(Thread.currentThread().getName() + ": consumed " + value);
			// signal producer thread that, buffer may be empty now 
			System.out.println(Thread.currentThread().getName() + 
						" : Signalling that buffer may be empty now"); 
			bufferNotEmpty.signalAll(); 
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
		finally { 
			lock.unlock(); 
		} 
	} 
}
class Producer2 implements Runnable  { 
	Buffer sharedBuffer; 
	int repeat = 5;
	public Producer2(Buffer sharedBuffer) {  
		this.sharedBuffer = sharedBuffer; 
	} 
	public void run() { 
		while((repeat--) > 0) {
			sharedBuffer.put(); 
		} 
	}
} 
class Consumer2 implements Runnable  { 
	Buffer sharedBuffer; 
	int repeat = 5;
	public Consumer2(Buffer sharedBuffer) { 
		this.sharedBuffer = sharedBuffer; 
	} 
	public void run() {
		while((repeat--) > 0) {
			sharedBuffer.get(); 
		}
	}
}
