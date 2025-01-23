package locks.fairness;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockFairnessExample {

//	private Lock lockFairness = new ReentrantLock(); //this is unfair lock because 
													// there is no true inside Reentrant(true)
	private Lock lockFairness = new ReentrantLock(true); //this is fair lock
	//the order in which threads request for lock will be maintained here
	public void accessResource() {
		lockFairness.lock();
		
		try {
			System.out.println(Thread.currentThread().getName() + " acquired the lock");
			Thread.sleep(1000);
		}
		catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		finally {
			lockFairness.unlock();
			System.out.println(Thread.currentThread().getName()+" released the lock");
		}
	}
}
