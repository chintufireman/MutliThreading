package producer.consumer;

public class Main {
	public static void main(String[] args) {
		Company cmpny = new Company();
		Producer p =new Producer(cmpny);
		Consumer c = new Consumer(cmpny);
		p.start();
		c.start();
	}
}
