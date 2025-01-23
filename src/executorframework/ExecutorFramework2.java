package executorframework;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ExecutorFramework2 {
	public static void main(String[] args) {

		long start = System.currentTimeMillis();
		ExecutorService executorService = Executors.newFixedThreadPool(9);
		// whenever u want to create thread pool use above method
		// if u give 9 as a parameter then every thread will take
		// 1 number to execute if u have given 3 then every thread
		// will take 3 number
		for (int i = 1; i < 10; i++) {
			final int finalI = i;
			Future<?> submit=executorService.submit(() -> {
				int result = fact(finalI);
				System.out.println(result);
			});// takes runnable or callable 
			
			//future- if submit returns something then it can be holded inside the future
		}
		executorService.shutdown();
		// this program will keep running if u wont shutdown
		// after this u won't be able to submit new task through
		// executorService

		// main thread wont wait for executor service and print
		// below line, it orderly starts shutdown every tasks
		// before fully getting completed it waits for
		// previously submitted task to get completed
		// that's why main wont wait for
		// every task to get completed

		try {
			executorService.awaitTermination(100, TimeUnit.SECONDS);
			
		} catch (InterruptedException exception) {

		}
		System.out.println("Time " + (System.currentTimeMillis() - start));
	}

	static int fact(int v) {

		try {
			Thread.sleep(1000);
		} catch (Exception e) {
		}

		int result = 1;
		for (int i = 1; i < v + 1; i++) {
			result *= i;
		}
		return result;
	}
}
