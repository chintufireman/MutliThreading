package locks;

import locks.fairness.LockFairnessExample;

public class Test {
	public static void main(String[] args) throws InterruptedException {
		BankAccount bankAccount = new BankAccount();
		Runnable task = new Runnable() {

			@Override
			public void run() {
				bankAccount.withdraw(40);
			}
		};

		Thread t1 = new Thread(task, "thread 1");
		Thread t2 = new Thread(task, "thread 2");

		t1.start();
		t2.start();
		t1.join();
		t2.join();

	}
}
