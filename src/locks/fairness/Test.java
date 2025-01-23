package locks.fairness;

import locks.fairness.LockFairnessExample;

public class Test {
	public static void main(String[] args) throws InterruptedException {
		
		LockFairnessExample lock = new LockFairnessExample();
		Runnable task2 = new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				lock.accessResource();
			}
		};
		
		Thread t3 = new Thread(task2, "Thread t3");
		Thread t4 = new Thread(task2, "Thread t4");
		Thread t5 = new Thread(task2, "Thread t5");
		
		t3.start();
		Thread.sleep(1000);
		t5.start();
		Thread.sleep(1000); 
		//this is for main thread to sleep for some time so that
		//these threads gets some time to request for lock and that's why order will be 
		//always maintained
		t4.start();
		
	}
}
