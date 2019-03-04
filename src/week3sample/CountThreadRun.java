package week3sample;

class CountThreadRun implements Runnable {
	private String name;
	public CountThreadRun(String s) {
		name = s;
	}
	String getName() {return name;}
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
			System.out.println(this.getName() + ":" + i);
		}
	}

	public static void main(String[] args) {
	      Thread t1 = new Thread(new CountThreadRun("t1"));
	      Thread t2 = new Thread(new CountThreadRun("\tt2"));
	      t1.start(); t2.start();
	   }
}