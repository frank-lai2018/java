package thread;

import java.util.stream.Stream;

/**
 * 一般生產者消費者例子(最終版)，生產者產出一個產品，等待產品被消費者使用後在產出一個商品
 * 此種方式，利用notifyAll喚醒所有wait的執行緒，但不需要有個while循環，當被喚醒時去檢查，可不可以消費或生產，
 * 如果用IF會變程式是繼續執行下去，
 * */
public class ProducerAndConsumerEx3 {
	private int product = 0;

	private final Object LOCK = new Object();

	private boolean isProduce = false;

	private void producer() {
		synchronized (LOCK) {
			while (isProduce) {
				try {
					LOCK.wait();// 等待消費者消費完
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("P->" + product++);
			LOCK.notifyAll();// 告訴消費者者已生產完
			isProduce = true;
			
		}
	}

	private void consumer() {
		synchronized (LOCK) {
//			if (!isProduce) { //此處不能用IF因為其他執行緒都停在wait狀態，如果沒有while循環，被喚醒後會繼續執行消費
			while (!isProduce) {
				try {
					LOCK.wait();// 等待生產者生產完
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} 
			System.out.println("C->" + product);
			LOCK.notifyAll();
			isProduce = false;
			
		}
	}

	public static void main(String[] args) {
		ProducerAndConsumerEx3 pa = new ProducerAndConsumerEx3();

		Stream.of(1, 2, 3, 4).forEach(n -> new Thread("producer") {
			@Override
			public void run() {
				while (true) {
					pa.producer();
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}.start());
		Stream.of(1, 2, 3, 4).forEach(n -> new Thread("consumer") {
			@Override
			public void run() {
				while (true) {
					pa.consumer();
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
		}.start());
	}
}
