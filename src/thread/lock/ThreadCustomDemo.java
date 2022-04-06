package thread.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class DataCustom{
	
	private int flag = 1; //1:AA 2:BB 3:CC
	
	Lock lock = new ReentrantLock(); 
	//建立
	Condition c1  =lock.newCondition();//AA
	Condition c2  =lock.newCondition();//BB
	Condition c3  =lock.newCondition();//CC
	
	public void printAA(int loop) {

		lock.lock();
		try {
			// 判別flag
			while (flag != 1) {
				c1.await();
			}

			for (int i = 0; i < 3; i++) {
				System.out.println(Thread.currentThread().getName() + " :: " + i + " ：輪數：" + loop);
			}
			
			//通知BB
			flag = 2;
			c2.signal();

		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}

	}
	public void printBB(int loop) {
		
		lock.lock();
		try {
			// 判別flag
			while (flag != 2) {
				c2.await();
			}
			
			for (int i = 0; i < 6; i++) {
				System.out.println(Thread.currentThread().getName() + " :: " + i + " ：輪數：" + loop);
			}
			
			//通知CC
			flag = 3;
			c3.signal();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
		
	}
	public void printCC(int loop) {
		
		lock.lock();
		try {
			// 判別flag
			while (flag != 3) {
				c3.await();
			}
			
			for (int i = 0; i < 9; i++) {
				System.out.println(Thread.currentThread().getName() + " :: " + i + " ：輪數：" + loop);
			}
			
			//通知AA
			flag = 1;
			c1.signal();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
		
	}
}

public class ThreadCustomDemo {
	
	public static void main(String[] args) {
		
		DataCustom dataCustom = new DataCustom();
		
		new Thread(() -> {
			for(int i = 0 ;i<10;i++) {
				dataCustom.printAA(i);
			}
		},"AA").start();
		new Thread(() -> {
			for(int i = 0 ;i<10;i++) {
				dataCustom.printBB(i);
			}
		},"BB").start();
		new Thread(() -> {
			for(int i = 0 ;i<10;i++) {
				dataCustom.printCC(i);
			}
		},"CC").start();
		
	}
	
}
