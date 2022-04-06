package thread.sync;

import static org.hamcrest.CoreMatchers.startsWith;

class Share {
	private int number = 0;

	public synchronized void add() throws InterruptedException {

		// 判別條件，因為wait有個特性，就是在哪裡等，被喚醒後就重哪裡開始執行，所以不能使用if，需要使用while
//		if (number != 0) {
//			this.wait();
//		}
		while (number != 0) {
			this.wait();
		}

		// 程式邏輯
		this.number++;
		System.out.println(Thread.currentThread().getName()+" :: "+number);

		// 喚醒其他
		this.notifyAll();

	}

	public synchronized void subtraction() throws InterruptedException {
		// 判別條件，因為wait有個特性，就是在哪裡等，被喚醒後就重哪裡開始執行
//		if (number == 0) {
//			this.wait();
//		}
		while (number == 0) {//必需使用while，不然會有虛假喚醒問題
			this.wait();
		}

		// 程式邏輯
		this.number--;
		System.out.println(Thread.currentThread().getName()+" :: "+number);

		// 喚醒其他
		this.notifyAll();

	}
}

public class ThreadDemo1 {

	public static void main(String[] args) {
		Share share = new Share();

		new Thread(() -> {
			for (int i = 1; i <= 10; i++) {
				try {
					share.add();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		},"AA").start();
		
		new Thread(() -> {
			for (int i = 1; i <= 10; i++) {
				try {
					share.add();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		},"CC").start();
		
		new Thread(() -> {
			for (int i = 1; i <= 10; i++) {
				try {
					share.subtraction();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		},"BB").start();
		new Thread(() -> {
			for (int i = 1; i <= 10; i++) {
				try {
					share.subtraction();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		},"DD").start();
	}
}
