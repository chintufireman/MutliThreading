package locks.deadlocks;

public class DeadLockExample {
	public static void main(String[] args) {
		Pen pen = new Pen();
		Paper paper = new Paper();
		
		Thread thread1  = new Thread(new Task1(pen,paper), "Thread-1");
		Thread thread2  = new Thread(new Task2(pen,paper), "Thread-2");
		
		thread1.start();
		thread2.start();
	}
}

class Pen {

	synchronized void writeWithPenAndPaper(Paper paper) {
		System.out.println(Thread.currentThread().getName() + " is using pen " + this + "and trying to write on paper");
		paper.finishWriting();
	}

	synchronized void finishWriting() {
		System.out.println(Thread.currentThread().getName() + " finished using pen " + this);
	}
}

class Paper {
	synchronized void writeWithPaperAndPen(Pen pen) {
		System.out.println(Thread.currentThread().getName() + " is using paper " + this + "and trying to write with pen");
		pen.finishWriting();
		//when an function is defined with sync keyword it acquires
		//intrinsic lock for itself of the object the method belongs
		//to means in this case of paper .
	}

	
	synchronized void finishWriting() {
		System.out.println(Thread.currentThread().getName() + " finished using paper " + this);
	}
}

class Task1 implements Runnable{
	Pen pen;
	Paper paper;
	
	Task1(Pen pen, Paper paper){
		this.pen = pen;
		this.paper = paper;
	}
	
	@Override
	public void run() {
		synchronized (paper) {
			pen.writeWithPenAndPaper(paper);
		}
		
	}
	
}

class Task2 implements Runnable{
	Pen pen;
	Paper paper;
	
	Task2(Pen pen, Paper paper){
		this.pen = pen;
		this.paper = paper;
	}
	
	@Override
	public void run() {
		
//		synchronized (pen) {
//			paper.writeWithPaperAndPen(pen);
//		}
//		you can use this way or u can add syncrhonized block in task1
// works both way
		paper.writeWithPaperAndPen(pen);
		
		//creates deadlock
		//paper waits for pen and pen waits for paper
		
		//to remove deadlock we have to ensure that every
		//thread acquires lock in consistent order
		//they should always acquire resouces in same sequence
		
		//u can syncyronize block on pen resource first here
		//or synchronize block for paper in task1 thread
		//and inside that call the pen function with paper or vice
		//versa
	}
	
}