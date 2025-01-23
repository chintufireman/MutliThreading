package producer.consumer;

//producer and consumer problem
/*
1.Create two methods for which we have to make them synchronized with keyword synchronized
2.On what basis we are going to make other methods in non runnable mode by logically we have to 
  write that code, like in below code we using boolean variable to make threads in runnable and
  non runnable by using notify() and wait() method.
3.after creating above methods we have to make them Inter-thread Communication which can be 
  achieved by wait(), sleep(),notify().
4.But before creating Inter-thread Communication we have to create two threads Producer and Consumer.
5.and thus we write logic inside threads to be run and also make two methods in synch.
6.In following code Producer class uses Company object to call produceItem method which will 
  do producer's action and Consumer class will call consumerItem method which will call method
  consumerItem() after execution of producerItem() so that both of them stay in synchronization
*/
public class Company {
	int n = 1;
	boolean chanceOfConsumer = false;

	// chanceOfConsumer = false : chance of producer to run
	// chanceOfProducer = true : chance of consumer to run
	synchronized void produceItem(int n) {
		if (chanceOfConsumer) {
			try {
				wait();
			} 
			catch (InterruptedException e) {
			}
		}
		this.n = n;
		System.out.println("Produced" + this.n);
		chanceOfConsumer = true;
		notify();
	}

	synchronized int consumeItem() {
		if (!chanceOfConsumer) {
			try {
				wait();
			} 
			catch (InterruptedException e) {
			}
		}
		System.out.println("Consumed" + this.n);
		chanceOfConsumer = false;
		notify();
		return this.n;
	}
}
