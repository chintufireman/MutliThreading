package executorframework;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureExample {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();

//		Future<Integer> submit = newSingleThreadExecutor.submit(()->421);
		Future<?> submit = newSingleThreadExecutor.submit(() -> System.out.println("hello"), "SUCCESS");
		// future holds some value inside the submit
		// Future.get() waits if necessary for computation to complete

		System.out.println(submit.get().toString());

//		if(submit.isDone()) {
//			//isdone doesn't wait for task to get completed
//			System.out.println("Task is done");
//		}
		newSingleThreadExecutor.shutdown();
		System.out.println(newSingleThreadExecutor.isShutdown());
		// checks if executoservice is shutdown

		Thread.sleep(1);
		System.out.println(newSingleThreadExecutor.isTerminated());
		// it will give false but sleeping thread for 1ms it takes time
		// to see if all tasks are completed

		ExecutorService executors = Executors.newFixedThreadPool(3);

		Callable<Integer> callable1 = () -> {
			System.out.println("task1");
			return 2;
		};
		Callable<Integer> callable2 = () -> 216;
		Callable<Integer> callable3 = () -> 25;

		List<Callable<Integer>> asList = Arrays.asList(callable1, callable2, callable3);

		List<Future<Integer>> invokeAll = executors.invokeAll(asList);
		System.out.println(invokeAll.get(0).get());
		executors.shutdown();

		ExecutorService executorService = Executors.newSingleThreadExecutor();
		Future<?> submit2 = executorService.submit(() -> {
			try {
				Thread.sleep(2000);

			} catch (Exception e) {

			}
			System.out.println("Hello2");
			return 43;
		});
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			// TODO: handle exception
		}
		submit2.cancel(true);
		System.out.println("cancelled? "+submit.isCancelled());
		System.out.println(submit.isDone());

		executorService.shutdown();
	}
}
