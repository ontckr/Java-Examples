package week3sample;

public class CountThread extends Thread {
	public CountThread(String s) {

	}
	void mySleep (int time) {
		try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
	}
	public void run() {
		for (int i = 0; i < 10; i++) {
			mySleep(500); // milliseconds
			System.out.println(this.getName() + ":" + i + ":" +i);
		}
	}

	public static void main(String[] args) {
	      Thread t1 = new CountThread("t1");
	      Thread t2 = new CountThread("\tt2");
		Thread t3 = new CountThread("\tt3");
	      t1.start(); t2.start();
	   }
}
