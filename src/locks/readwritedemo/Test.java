package locks.readwritedemo;

public class Test {

	public static void main(String[] args) throws InterruptedException {
		ReadWriteLockExample counter = new ReadWriteLockExample();

		Runnable readTask = new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 10; i++) {
					System.out.println(Thread.currentThread().getName() 
							+ " read: " + counter.getCount());
				}
			}
		};
		Runnable writeTask = new Runnable() {
			
			@Override
			public void run() {
				for(int i=0;i<10;i++) {
					counter.increment();
					System.out.println(Thread.currentThread().getName()
							+" incremented");
				}
			}
		};
		
		Thread writeThread = new Thread(writeTask, "Thread write");
		Thread readThread1 = new Thread(readTask, "Thread Read 1");
		Thread readThread2 = new Thread(readTask, "Thread Read 2");
		
		writeThread.start();
		readThread1.start();
		readThread2.start();  
		
		writeThread.join();
		readThread1.join();
		readThread2.join();
	}
}
