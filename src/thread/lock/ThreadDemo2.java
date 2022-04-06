package thread.lock;

import static org.hamcrest.CoreMatchers.startsWith;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class LockShare {
	private int number = 0;

	private Lock lock = new ReentrantLock();

	private Condition condition = lock.newCondition();

	public  void add() throws InterruptedException {

		lock.lock();

		// 判別條件，因為wait有個特性，就是在哪裡等，被喚醒後就重哪裡開始執行，所以不能使用if，需要使用while

		try {
			while (number != 0) {
				condition.await();
			}

			// 程式邏輯
			this.number++;
			System.out.println(Thread.currentThread().getName() + " :: " + number);

			// 喚醒其他
			condition.signalAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}

	}

	public  void subtraction() throws InterruptedException {
		lock.lock();

		// 判別條件，因為wait有個特性，就是在哪裡等，被喚醒後就重哪裡開始執行，所以不能使用if，需要使用while

		try {
			while (number == 0) {
				condition.await();
			}

			// 程式邏輯
			this.number--;
			System.out.println(Thread.currentThread().getName() + " :: " + number);

			// 喚醒其他
			condition.signalAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}


	}
}

public class ThreadDemo2 {

	public static void main(String[] args) {
		LockShare share = new LockShare();

		new Thread(() -> {
			for (int i = 1; i <= 10; i++) {
				try {
					share.add();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "AA").start();

		new Thread(() -> {
			for (int i = 1; i <= 10; i++) {
				try {
					share.add();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "CC").start();

		new Thread(() -> {
			for (int i = 1; i <= 10; i++) {
				try {
					share.subtraction();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "BB").start();
		new Thread(() -> {
			for (int i = 1; i <= 10; i++) {
				try {
					share.subtraction();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "DD").start();
	}
}
