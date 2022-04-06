package thread.support;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {

	
	public static void main(String[] args) {
		
		//創建CountDownLatch，給定初始值
		CountDownLatch countDownLatch = new CountDownLatch(6);
		
		for(int i = 1 ; i<= 6;i++) {
			new Thread(() -> {
				System.out.println(Thread.currentThread().getName()+" 執行結束...");
				
				//執行緒執行結束 -1
				countDownLatch.countDown();
			},i+"執行緒").start();
		}
		
		try {
			//使用await會等待countDownLatch變成0才會執行後續程式
			countDownLatch.await();
			
			System.out.println(Thread.currentThread().getName()+" 執行結束...");
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
}
