package producer.consumer;

public class Consumer extends Thread {
	Company c;

	public Consumer(Company c) {
		this.c=c;
	}

	public void run() {
		while (true) {
			this.c.consumeItem();
			try {
				Thread.sleep(2000);
			} catch (Exception e) {

			}
		}
	}
}
