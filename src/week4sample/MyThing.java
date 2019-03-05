package week4sample;

public class MyThing {
	static private int count = 0;
	private int serialNum;

	private static	synchronized int getSN() {
		return count++;
	}

	private class Thread1 implements Runnable {
	   public void run() {
		   String name = Thread.currentThread().getName();
//		   serialNum = count++; // Thread unsafe
		   serialNum = getSN(); // Thread Safe
		   System.out.println(name + ": serialNum = " + serialNum + " count = " + count);
	  }
	}

	private class Thread2 implements Runnable {
	   public void run() {
		   String name = Thread.currentThread().getName();
//		   serialNum = count++; // Thread unsafe
		   serialNum = getSN(); // Thread Safe
		   System.out.println(name + ": serialNum = " + serialNum + " count = " + count);
	   }
	}
	public static void main(String args[]) {
		MyThing d = new MyThing ();
		Thread t1 = new Thread(d.new Thread1(), "Thread1");
		Thread t2 = new Thread(d.new Thread2(), "Thread2");
		t1.start();
		t2.start();
	 }
}
