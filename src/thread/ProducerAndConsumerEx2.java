package thread;

/**
 * 一般生產者消費者例子，生產者產出一個產品，等待產品被消費者使用後在產出一個商品
 * */
public class ProducerAndConsumerEx2 {
	
	private int product = 0;
	
	private final Object LOCK = new Object();
	
	private boolean isProduce = false;
	
	private void producer() {
		synchronized (LOCK) {
			if(isProduce) {
				try {
					LOCK.wait();//等待消費者消費完
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}else {
				System.out.println("P->"+product++);
				LOCK.notify();//告訴消費者者已生產完
				isProduce = true;
			}
		}
	}
	
	private void consumer() {
		synchronized (LOCK) {
			if(isProduce) {
				System.out.println("C->"+product);
				LOCK.notify();
				isProduce = false;
			}else {
				try {
					LOCK.wait();//等待生產者生產完
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}


	public static void main(String[] args) {
		ProducerAndConsumerEx2 pa = new ProducerAndConsumerEx2();
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
