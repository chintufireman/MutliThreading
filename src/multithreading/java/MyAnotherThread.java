package multithreading.java;

//creating thread using Thread class
public class MyAnotherThread extends Thread {
	public void run() {
		//task for thread
		for (int i = 10; i >=1; i--) {
			System.out.println("Thread 2 = "+i);
			try {
				Thread.sleep(2000);
			}
			catch (Exception e) {
			}
		}
	}
}
