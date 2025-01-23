package multithreading.java;

//thread operations
class UserThread extends Thread {
	public void run() {
		// task of thread
		System.out.println("this is user defined thread");
	}
}

public class ThreadOp {
	public static void main(String[] args) {
		System.out.println("Program Started ");

		int x = 84 + 234;
		System.out.println(x);
		// Thread name...
		Thread t = Thread.currentThread();
		System.out.println(t.getName() + "  " + t.getId());
		// set name of thread
		t.setName("HarshThread");
		System.out.println(t.getName() + "  " + t.getId());
		try {
			Thread.sleep(5000);
		} catch (Exception e) {
		}
		UserThread thread = new UserThread();
		thread.start();
		System.out.println("Program ended");
	}
}
