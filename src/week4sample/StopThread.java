package week4sample;

public class StopThread {
	public static void main(String args[]) throws InterruptedException { 
		//BadThread myServer = new BadThread(); 
		GoodThread myServer = new GoodThread(); 
		Thread t1 = new Thread(myServer, "T1"); 
		t1.start(); // stop our thread 
		System.out.println(Thread.currentThread().getName() + " is stopping  thread"); 
		myServer.stop(); //Let's wait to see thread stopped 
		System.out.println(Thread.currentThread().getName() + " is finished now"); 
	} 
}
class BadThread implements Runnable{ 
	private volatile Boolean done = false; 
	public void run() { 
		synchronized (done) {
			while(!done){ 
				System.out.println("BadThread is running....."); 
			}
		}
		System.out.println("BadThread is stopped...."); 
	} 
	public void stop(){ 
		synchronized(done) {
		      done = true;
		 }
	} 
} 

class GoodThread implements Runnable{ 
	private volatile Boolean done = false; 
	public void run() { 
		while (true) {
			System.out.println("GoodThread is running.....");
			synchronized(done) {
				if ( done ) {
					break;
	           }
			}
		}
		System.out.println("GoodThread is stopped...."); 
	} 
	public void stop(){ 
		synchronized(done) {
		      done = true;
		 }
	} 
} 
