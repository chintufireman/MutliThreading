package multithreading.java;

import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


/*
*1. It is Thread class which provides methods to perform operations with threads.
*2. This thread class is present in java.lang so we dont need to import this pkg.
*3. some important thread methods:-
*	i) public String getName() - returns thread name;
*	ii) public void setName(String name) - set the name of thread.
*	iii) public void run()- contains the task of thread.
*	iv) starts thread by allocating resources.
*	v) public long getId() - returns the id of thread.
*	vi) setPriority and getPriority() - set and get the priority.
*	vii)sleep(), join(),interrupt(),resume(),stop(),....etc.
*/


//creating out first thread using runnable
public class MyThread implements Runnable {

	@Override
	public void run() {
		// task for thread
		int[] c = new int[1];
		c[0] = 1;
		IntStream numsStream = IntStream.empty();
		/*
		 * IntStream.range(1, 10).forEach(x -> { System.out.println(1 * c[0]); c[0] =
		 * c[0]+1; });
		 */
		/* IntStream.range(1, 10).forEach(x -> System.out.println(x*1)); */
		for (int i = 1; i <= 10; i++) {
			System.out.println("Thread 1 = "+i*1);
			try {
				Thread.sleep(1000);
			}
			catch (Exception e) {
				
			}
		}

	}
 
	//Inside main  method main thread will start which will start execution of 
	//both threads and after that its job is done and other two will run
	//now in this program total 3 threads were executed
	public static void main(String[] args) {
		//create object of MyThread class
		MyThread thread = new MyThread();
		Thread t1 = new Thread(thread);
		//object of another thread
		MyAnotherThread t2 = new MyAnotherThread();
		t1.start();
		t2.start();
	}
}
