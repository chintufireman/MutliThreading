package volatilexample;

import java.util.concurrent.atomic.AtomicInteger;

public class VolatileCounter {

//	volatile int counter = 0;

	//if u use volatile keyword here it wont solve the issue of simultaneous access
//	of counter variable, volatile is used when u want to access data variable from 
//	main memory and not from local copy which was created inside the thread
//	this is called as atomicity
	
//	what we want is every operation should be isolated means in this case two threads are 
//	trying to access same variable at same time and what we want is only one thread should
//	access the variable at a time
//	though u can use lock or synchronized but there is also another way to do this
	
//	java has given us some classes AtomicInteger, Atomicboolean, etc so that u can achieve
//	atomicity without using locks or synchronized keyword and u can achieve isolation
	
	AtomicInteger counter = new AtomicInteger(0);
	//this variable is actually thread safe
	
	void increment() {
		counter.incrementAndGet();
	}

	int getCounter() {
		return counter.get();
	}

	public static void main(String[] args) throws InterruptedException {
		VolatileCounter counter = new VolatileCounter();
		
		Thread t1 = new Thread(()->{
			for(int i=0;i<1000;i++) {
				counter.increment();
			}
		});
		Thread t2 = new Thread(()->{
			for(int i=0;i<1000;i++) {
				counter.increment();
			}
		});
		
		t1.start();
		t2.start();
		
		t1.join();
		t2.join();
		
		System.out.println(counter.getCounter());
	}
}
