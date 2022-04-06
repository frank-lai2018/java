package thread.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class Callable1 implements Callable<Integer>{

	@Override
	public Integer call() throws Exception {
		System.out.println("Callable1.........");
		return 200;
	}
	
}

public class FuTureTaskTest {

	
	public static void main(String[] args) {
		FutureTask<Integer> futureTask1 = new FutureTask<Integer>(new Callable1());
		
		FutureTask<Integer> futureTask2 = new FutureTask<Integer>(() -> {
			System.out.println("FuTrueTask2.........");
			return 201;
		});
		
		new Thread(futureTask1,"AA").start();
		new Thread(futureTask2,"BB").start();

		while(!futureTask1.isDone()) {
			System.out.println("futureTask1 wait.....");
		}
		while(!futureTask2.isDone()) {
			System.out.println("futureTask2 wait.....");
		}
		
		try {
			System.err.println(futureTask1.get());
			System.err.println(futureTask2.get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
