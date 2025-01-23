package executorframework;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduleExecutorServiceExample {
	public static void main(String[] args) {
		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
		scheduler.schedule(() -> System.out.println("Hello in 5 seconds"), 5, TimeUnit.SECONDS);
		// for 5 seconds it will be waited and then executed

		scheduler.scheduleAtFixedRate(
				() -> System.out.println("task executed in every 5 sec"), 
				5, 
				5, 
				TimeUnit.SECONDS);
		// when u do same shit with schedule the task will go first in queue
		// so scheduler waits for it to get finished but when u use
		// scheduleatfixedrate it does perodic task so if u call shutdown immediately
		// the task won't get chance to get inside the queue and these tasks
		// needs periodically to be queued inside the scheduler
		// so to prevent from immediate shutdown we will wait for
		// shutdown some fixed amount of time like 30sec and this can be achieved
		// in same way by creating new task for shutdown which will be started after
		// N given seconds suppose 30sec
		
		
		
		//scheduled at fixed delay
		//with scheduledAtFixRate the task will get executed after every 5 seconds or
		//whatever time u have given, so if task suppose take 10sec then it doesn't
		//if its completed or not the next is going to be executed even though first one is
		//running
		
		//now in delay there is difference, in this function instead of giving period time
		//u have to give delay time which tells the function to after how much time
		//the scheduler should execute the next after completion of previous task
		scheduler.scheduleWithFixedDelay(
				() -> System.out.println("task executed in every 5 sec delay"), 
				5, 
				5, 
				TimeUnit.SECONDS
				);
		
		
 		scheduler.schedule(() -> {
			System.out.println("Intiating shutdown.....");
			scheduler.shutdown();
		}, 60, TimeUnit.SECONDS);

	}
}
