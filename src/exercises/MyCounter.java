package exercises;


import java.util.PriorityQueue;
import java.util.concurrent.locks.ReentrantLock;

public class MyCounter implements Runnable{
	
	static int counter = 1;
	static PriorityQueue<String> queue = new PriorityQueue<>();
	
	
	static ReentrantLock counterLock = new ReentrantLock(true);
	
	public MyCounter() {
		queue.add("a");
		queue.add("b");
		queue.add("c");
		queue.add("d");
		queue.add("e");
		queue.add("f");
		queue.add("g");
		queue.add("h");
		
	}
	
	static void incrementCounter() {
		counterLock.lock();
		try {
			if(queue.isEmpty()) {
				System.out.println(Thread.currentThread().getName() + ": "+ counter);				
				counter ++;
			}else {
				System.out.println(Thread.currentThread().getName() + ": "+ queue.poll());
			}
		} finally {
			counterLock.unlock();
		}
	}
	
	@Override
	public void run() {
		while(counter < 14) {
			try {
				Thread.sleep(1000);				
				incrementCounter();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
}
