package executorframework;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class CountDownLatchExample {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService service = Executors.newFixedThreadPool(3);
		Future<String> submit = service.submit(new Task());
		Future<String> submit2 = service.submit(new Task());
		Future<String> submit3 = service.submit(new Task());
		
		submit.get();
		submit2.get();
		submit3.get();
		System.out.println("All tasks finished ........");
		//here u can see here there are multiple task for which we have to wait for each
		//task, now suppose u have to do this print operation only after completion of 
		//all 3 task and get value in that case u have to call get method of future 3 times
		//suppose u have 1000 tasks then in that case 1000 times u have to call get method
		//else main will shutdown immediately
		
		// u can also use invokeAll() of executorservice and get every o/p in lists
		// but there is also another way to simplify this waiting
		
		//the main thing is u have to make main thread wait for some dependacy like in this
		//case execution of 3 tasks, and u dont have to create manual threads 
		//so u can use countdownlatch, latch means lock and count down means counting 
		//in reverse
		
		service.shutdown();
		
		int numberOfTasks = 3;
		ExecutorService service2 = Executors.newFixedThreadPool(numberOfTasks);
		//creating object of countdownlatch
		CountDownLatch countDownLatch = new CountDownLatch(numberOfTasks);
		//this is used when we have to make one or more than one threads to wait
		//then we use countdown latch, in its constructor we have to give number of
		//tasks for which we have to wait
		
		//u have to use countdownlatch in ur task
		
		service2.submit(new DependentService(countDownLatch));
		service2.submit(new DependentService(countDownLatch));
		service2.submit(new DependentService(countDownLatch));
		
		countDownLatch.await();
		// u are creating object of count down latch and sending it to dependent service
		// when the latch becomes 0 the await method will return and main method
		//will start running 
		
		//countDownLatch.await(1,TimeUnit.SECONDS);
		//see in this case the main will wait for 1 second only even if tasks 
		// are taking time of 5 sec, the main will print after 1 sec and after 5 sec
		//it will print all task but if u want to shutdown tasks immediately the use
		//service2.shutdownNow() and it will get closed .		
		service2.shutdown();
		System.out.println("Main.....");
	}
	
}
class Task implements Callable<String>{

	@Override
	public String call() throws Exception {
		
		Thread.sleep(2000);
		System.out.println(Thread.currentThread().getName() + " service started ");
		return null;
	}
	
}
class DependentService implements Callable<String>{
	
	final private CountDownLatch latch;
	
	public DependentService(CountDownLatch latch) {
		this.latch=latch;
	}
	@Override
	public String call() throws Exception {
		try {
			Thread.sleep(5000);
			System.out.println(Thread.currentThread().getName() + " service started ");
		}
		finally {
			latch.countDown();
			//for first time when it will run 
			// it will decrement with one and everytime this method is called
			//it will decrement by one
		}
		
		return null;
	}
	
}