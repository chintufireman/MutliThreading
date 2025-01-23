package thread.communication;

public class ThreadCommunication {
	
	public static void main(String[] args) {
		SharedResource resource = new SharedResource();
		Thread t1 = new Thread(new Producer(resource));
		Thread t2 = new Thread(new Consumer(resource));
		
		t1.start();
		t2.start();
	}
}

class SharedResource {
	int data;
	boolean hasData;

	synchronized void produce(int value) {
		while(hasData) {
			try {
				wait();
			}
			catch(InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
		data=value;
		hasData=true;
		System.out.println("Produced: " + value);
		notify();
	}

	synchronized int consume() {
		while(!hasData) {
			try {
				wait();
			}
			catch(InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
		hasData=false;
		System.out.println("Consumed: "+data);
		notify();
		return this.data;
	}
}

class Producer implements Runnable {
	SharedResource resource;

	public Producer(SharedResource resource) {
		this.resource = resource;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			resource.produce(i);
		}
	}
}

class Consumer implements Runnable{

	SharedResource resource;
	
	Consumer(SharedResource resource){
		this.resource = resource;
	}
	
	@Override
	public void run() {
		for(int i=0;i<10;i++) {
			resource.consume();
		}
		
	}
	
}