package locks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {

	private int balance = 100;

	private Lock lock = new ReentrantLock();// Lock class's implementation class

	public void outerMethod() {
		lock.lock();
		try {
			System.out.println("Outer method");
			innerMethod();
			
		}finally {
			System.out.println("unlocking outer lock");
			lock.unlock();
		}
	}
	
	public void innerMethod() {
		lock.lock();
		
		//what happens here is in outerMethod the lock was already there
		//and that lock wasn't unlocked 
		//the current method is trying to acquire lock again which is not available
		//this will create DEADLOCK condition
		//because inner method depends on outerMethod to unlock 
		//and outer is dependent on inner to finish the execution
		
		//but this might have happened if java was not smart
		//since the implementation of Lock class is ReentrantLock means that 
		// we can re enter inside the lock again
		// because same thread is running and we acquired lock for that only
		// so it will reacquire lock
		
		//u have the main key of house so u can go anywhere in the house 
		// and when u unlock this current innermethod it wont unlock the whole outerMethod
		// by current thread because because
		//ReentrantLock holds the count which shows how many time the lock has been acquired
		//so the current thread has Lock 2 times and 1 time unlock 
		//that's why it wont unlock whole method for all threads
		
		// yeah if u do 2 times unlock in inner then it will fully unlock all methods
		//for all threads
		try {
			System.out.println("inner method");
		}
		finally {
			System.out.println("unlocking iner lock");
			lock.unlock(); 
		}
	}
	
	public void withdraw(int amount) {
		System.out.println(Thread.currentThread().getName() + " is attempting to withdraw " + amount);

		try {
			if (lock.tryLock(1000, TimeUnit.MILLISECONDS)) {
				if (balance >= amount) {
					try {
						System.out.println(Thread.currentThread().getName() + " procedding with withdrawal ");
						Thread.sleep(3000);
						balance -= amount;
						System.out.println(Thread.currentThread().getName() + " completed with withdrawal with balance "
								+ balance);

					} catch (Exception e) {
						//just logging the interrupted exception is not enough
						//u need to re interrupt the current thread so that 
						//the state it is lost or interrupted is acquired again
						//by reinterrupting
						
						//if not interrupted there might be the case the 
						//risk of delaying the thread shutdown and losing the information
						//that the thread was interrupted probably without finishing the task
						
						Thread.currentThread().interrupt();
					} finally {
						lock.unlock();// to realease the lock
					}

				} else {
					System.out.println("Insufficient balance");
				}
			} else {
				System.out.println(Thread.currentThread().getName() + " could not acquire lock will try again later ");
			}
		} catch (Exception e) {
			Thread.currentThread().interrupt();
		}
		// lock.tryLock() if lock is free then it will return false

	}
}
