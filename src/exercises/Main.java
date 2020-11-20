package exercises;


public class Main {
	public static void main(String[] args) {
		
		MyCounter c1 = new MyCounter();
		Thread t1 = new Thread(c1);
		Thread t2 = new Thread(c1);
		
		t1.start();
		t2.start();
		
		
		
	}
}
