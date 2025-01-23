package synchronization;

public class Counter {
	private int count = 0;

	void increment() {
		synchronized (this) {
			//this means only one instance we are talking about
			//on which increment method is getting called
			//if multiple threads are trying to access this resource then
			//only one thread will access it 
			this.count++;
		}
	}

	int getCount() {
		return this.count;
	}
}
