package week3sample;

public class ExampleThread extends Thread {
	public void run() {
		System.out.println("Thread Running");
	}

	public static void main(String args[]) {
		Thread twoThread = new ExampleThread();
		twoThread.start();
		System.out.println("Main is running");
	}
}
