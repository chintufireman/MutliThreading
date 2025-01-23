package volatilexample;

public class VolatileExample {

	public static void main(String[] args) {
		SharedResource resource = new SharedResource();
		Thread writerThread = new Thread(()->{
			try {
				Thread.sleep(1000);
//				because we want before starting of setFlagtru()
//				we want it to run printFlag() and gets stuck inside the while loop
//				so cpu will start reader because this thread is going to sleep for 1 sec
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			resource.setFlagTru();
		});
		
		Thread readerThread = new Thread(()->{
			resource.printFlag();
		});
		
		writerThread.start();
		readerThread.start();
//		
//		the output will be unexpected u will see that readerThread is not printing anything
//		why so?
//		beacause readerThread is stuck inside the while loop which is checking if 
//		flag is true or not and if it is false then stay stucked in while loop else
//		print the flag is true
		
//		so why is this happening ?
//		beacause every thread each and every thread keeps copy of local variables
//		inside its cache
//		
//		every thread has its own cache and as soon as it sees any variable it saves 
//		inside its cache.
//		the writerthread changed the flag to true, which is inside the main memory that
//		is in our ram memory but in reader thread that variable is cached which is false
//		which has not been changed yet.
//		because of performance need every thread cache local variable so that the task
//		can be finished fast, but in this case flag is shared where one is setting the flag
//		and other is reading it.
//		
//		if we want to say jvm that dont cache this flag variable inside the thread then
//		we have to make it volatile
//		after making it volatile the readerThread will now not bring the variable 
//		from local memory but from main memory which is in ram

//		if things get more complicated then u cannot use volatile
	
	}
}
class SharedResource{
	private volatile boolean flag=false;
	
	void setFlagTru() {
		this.flag=true;
		System.out.println("writer thread made flag true");
	}
	
	void printFlag() {
		while(!flag) {
			//do nothing
		}
		System.out.println("flag is true");
	}
}