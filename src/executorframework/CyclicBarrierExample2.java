package executorframework;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierExample2 {

	public static void main(String[] args) {
		int numberOfSubsystems = 4;
		
		CyclicBarrier barrier =  new CyclicBarrier(numberOfSubsystems,()->{
			System.out.println("All subsystem are up...");
		});
		
//		when all the threads comes at barrier point then the barrier action will 
//		start executing that is run method of runnable inside the CyclicBarrier.
//		when all 4 threads come at gate then what u have to do i written there that
//		is inside the run method of runnable we are printing All subsystems are up
//		here main method is not getting blocked, what here is the use case we are 
//		actually waiting for all threads to get completed at some point and do our
//		remaining tasks that is it. 
		
//		so that is what we are waiting for the run logic inside the cyclic barrier given
//		and this is the actual use case of cyclic barrier
		
		//one more use case is we use this inside the matrix multiplication
		
		Thread webServerThread = new Thread(new SubSytem("webserver", 2000, barrier));
		Thread databaseThread = new Thread(new SubSytem("database", 4000, barrier));
		Thread cacheThread = new Thread(new SubSytem("cache", 3000, barrier));
		Thread messagingServiceThread = new Thread(new SubSytem("messaging service", 3500, barrier));
		
		webServerThread.start();
		databaseThread.start();
		cacheThread.start();
		messagingServiceThread.start();
		
		
	}
}

class SubSytem implements Runnable{

	private String name;
	private int initializationTime;
	private CyclicBarrier barrier;
	
	SubSytem(String name, int initializationTime, CyclicBarrier barrier){
		this.name=name;
		this.initializationTime = initializationTime;
		this.barrier = barrier;
	}
	
	public void run() {
		try {
			System.out.println(name +" initialization started");
			Thread.sleep(initializationTime);
			System.out.println(name+ " initialization completed ");
			barrier.await();
		}
		catch(InterruptedException | BrokenBarrierException exception) {
			Thread.currentThread().interrupt();
		}
	}
}