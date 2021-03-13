package thread;

/**
 * 一般生產者消費者例子
 * */
public class ProducerAndConsumerEx1 {
	
	private int product = 0;
	
	private final Object LOCK = new Object();
	
	private void producer() {
		synchronized (LOCK) {
			System.out.println("P->"+product++);
		}
	}
	
	private void consumer() {
		synchronized (LOCK) {
			System.out.println("C->"+product);
		}
	}


	public static void main(String[] args) {
		ProducerAndConsumerEx1 pa = new ProducerAndConsumerEx1();
		new Thread("producer") {
			@Override
			public void run() {
				while (true)
					pa.producer();
			}
		}.start();
		new Thread("consumer") {
			@Override
			public void run() {
				while (true)
					pa.consumer();
			}
		}.start();
	}
}
