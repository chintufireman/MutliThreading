package synchronization;

public class Test {
	public static void main(String[] args) throws InterruptedException {
		Counter counter = new Counter(); //shared object or shared resource
		
		MyThread t1 = new MyThread(counter);
		MyThread t2 = new MyThread(counter);
		
		//two threads will simultaneously work on object or increment the counter
		//if u want that only one thread access the method increment() and not at the 
		//same time then make that function synchronized 
		//1. if t1 thread is accessing that increment() then t2 will wait and vice versa
		//2. use synchronized keyword to make increment() synchronized
		//3. there is also one another way to synchronize suppose u dont want to 
		//sync whole method then u can use synchronized block{}
		
		//4.the part which is getting accessed by threads simultaneously is called as
		//critical section
		//5. this kind of situation where multiple threads tries to access same resource
		//is called as race condition 
		//6. the condition which removes races condition is called as MUTUAL EXCLUSION
		//7.mutual exclusion ensures that multiple threads does not access same resource
		//simultaneously 
		t1.start();
		t2.start();
		
		t1.join();
		t2.join();
		
		System.out.println(counter.getCount());
	}
}
