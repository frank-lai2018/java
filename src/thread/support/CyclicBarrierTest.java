package thread.support;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {

	//創建固定值
	private static final int NUMBER = 7;
	
	public static void main(String[] args) {
		
		CyclicBarrier cyclicBarrier = new CyclicBarrier(NUMBER, () -> {
			System.err.println("等待");
		});
		
	}
}
