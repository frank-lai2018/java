package thread.sync;

class Ticket{
	private int number = 30;
	
	public synchronized void sale() {
		if(number > 0) {
			System.out.println(Thread.currentThread().getName()+" :賣出: "+(number--)+" 剩下:" +number);
		}
	}
}

public class SaleTicket {
	
	
	public static void main(String[] args) {
		Ticket ticket = new Ticket();
		new Thread(() -> {
			//調用賣票方法
			for(int i=0;i<40;i++) {
				ticket.sale();
			}
		}, "AA").start();
		new Thread(() -> {
			//調用賣票方法
			for(int i=0;i<40;i++) {
				ticket.sale();
			}
		}, "BB").start();
		new Thread(() -> {
			//調用賣票方法
			for(int i=0;i<40;i++) {
				ticket.sale();
			}
		}, "CC").start();
	}

}
