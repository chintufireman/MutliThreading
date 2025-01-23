package executorframework;

public class ExecutorFramework {

	public static void main(String[] args) {
		
		long start = System.currentTimeMillis();
		Thread threads[] = new Thread[9];
 		
		for(int i=1;i<10;i++) {
			final int finalI = i;
			
			threads[i-1]= new Thread(()->{
				int result = fact(finalI);
				System.out.println(result);
			});
			//what will happen is the total time take code will be printed first
			//without completion of this loop, because main thread is not waiting
			
			//so what we can do is create array of threads and assign them new threads
			// then wait for each thread using join() method for there completion
			
			//previously without multithreading it was taking 9 sec but after using 
			//threads it takes only 9 thread
			threads[i-1].start();
		}
		for(Thread t : threads) {
			try {
				t.join();
			}
			catch(InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
		System.out.println("Time " + (System.currentTimeMillis() - start));
		
	}
	
	static int fact(int v) {
		
		try {
			Thread.sleep(1000);
		}catch(Exception e) {}
		
		int result=1;
		for(int i=1;i<v+1;i++) {
			result *= i;
		}
		return result;
	}
}
