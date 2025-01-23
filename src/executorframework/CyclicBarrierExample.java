package executorframework;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.Callable;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierExample {

	public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
//		You cannot use count down latch again, like there is no way 
//		to reuse it.
//		that's why we use CyclicBarrier
//		cyclic barrier does not block the main thread
		int numberOfTasks= 3;
		ExecutorService service = Executors.newFixedThreadPool(numberOfTasks);
		CyclicBarrier barrier = new CyclicBarrier(numberOfTasks);
		
		service.submit(new DependentService2(barrier));
		service.submit(new DependentService2(barrier));
		service.submit(new DependentService2(barrier));
//		
//		here there is no countdown to decrements the tasks completed one by one
//		here multiple threads will wait at specific point until all Threads have reached
//		that point.
//		when the last thread comes at that point or at that barrier 
//		all of the threads released.
		service.shutdown();
		System.out.println("Main ....");
		
//		Lets understand this with simple example
//		suppose u and ur friends want to go to a movie and there few friends have
//		not arrieved yet so u decide that until all friends come u won't got to
//		watch the movie.
//		
//		that's how cyclicbarrier works untill all threads comes towards barrier
//		no other thread will finish its work, so worker thread will wait at barrier.await()
//		until all threads have come to barrier
		
//		so as u know cyclic barrier does not block the main thread
//		so when to use this shit, when u want to make sure that certain number of threads
//		reach at some point.
//		
	}
}
class DependentService2 implements Callable<String>{
	CyclicBarrier barrier;
	
	public DependentService2(CyclicBarrier barrier) {
		this.barrier = barrier;
	}
	
	@Override
	public String call() throws Exception {
		System.out.println(Thread.currentThread().getName() + " Task completed");
		Thread.sleep(2000);
		System.out.println(Thread.currentThread().getName() + " is waiting at the barrier");
		barrier.await();
		return null;
	}
}
