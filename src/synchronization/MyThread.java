package synchronization;

import java.util.Iterator;

public class MyThread extends Thread{
	
	private Counter counter; //this is actually resource which current thread works on
	public MyThread(Counter counter) {
		this.counter=counter;
	}
	@Override
	public void run() {
		for(int i=0;i<1000;i++) {
			counter.increment();
		}
	}
	
	
}
