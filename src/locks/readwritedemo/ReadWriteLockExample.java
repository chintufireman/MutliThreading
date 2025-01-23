package locks.readwritedemo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockExample {

	private int count=0;
	
	private ReadWriteLock lock = new ReentrantReadWriteLock();
	
	private final Lock readLock = lock.readLock();
	private final Lock writeLock = lock.writeLock();
	
	//readLock will ensure that multiple threads can read at the same 
	//time the resource 
	//but this is only possible when writeLock is not acquired by 
	//some another thread on resource
	//because read and write lock related with each other,
	//they intercommunicate with each other
	//if write lock is not acquired then n number of threads can read
	//same resource but if it is acquired then no readLock for any thread
	
	public void increment() {
		writeLock.lock();
		
		try {
			count++;
			Thread.sleep(3000);
		}
		catch(InterruptedException e) {
			
		}
		finally {
			writeLock.unlock();
		}
		
	}
	public int getCount() {
		readLock.lock();
		
		try {
			return count;
		}
		finally {
			readLock.unlock();
		}
		
	}
}
