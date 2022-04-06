package thread.lock;

import java.util.concurrent.locks.ReentrantLock;

class LTicket{
	private int number = 300;
	
	private final ReentrantLock lock = new ReentrantLock();
	
	public void sale() {
		lock.lock();
		
		try {
			
			if(number > 0) {
				System.out.println(Thread.currentThread().getName()+" :賣出: "+(number--)+" 剩下:" +number);
			}
		}finally {
			lock.unlock();
		}
		
		
	}
}

public class LockSaleTicket {
	
	public static void main(String[] args) {
		LTicket ticket = new LTicket();
		new Thread(() -> {
			//調用賣票方法
			for(int i=0;i<400;i++) {
				ticket.sale();
			}
		}, "AA").start();
		new Thread(() -> {
			//調用賣票方法
			for(int i=0;i<400;i++) {
				ticket.sale();
			}
		}, "BB").start();
		new Thread(() -> {
			//調用賣票方法
			for(int i=0;i<400;i++) {
				ticket.sale();
			}
		}, "CC").start();
	}

}
