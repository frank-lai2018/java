package thread.future.completableFuture;

import java.util.concurrent.CompletableFuture;

import org.junit.Test;

public class ThenApplyTest {
	
	@Test
	public void testThenApply() {
		
		
		CompletableFuture<Integer> completableFuture =CompletableFuture.supplyAsync(() -> {
			 SmallTool.printTimeAndThread("執行緒開始...");
			 SmallTool.sleepMillis(100);
			 
			 return 500;
		}).thenApply((res) -> {
			SmallTool.printTimeAndThread("計算500+600開始...");
			SmallTool.sleepMillis(300);
			return 500+600;
		});
		
		

        SmallTool.printTimeAndThread(String.format("計算結果%d", completableFuture.join()));		
	}
	
	@Test
	public void testThenApplyAsync() {
		
		
		CompletableFuture<Integer> completableFuture =CompletableFuture.supplyAsync(() -> {
			SmallTool.printTimeAndThread("執行緒開始...");
			SmallTool.sleepMillis(100);
			
			return 500;
		}).thenApplyAsync((res) -> {
			SmallTool.printTimeAndThread("計算500+600開始...");
			SmallTool.sleepMillis(900);
			return res+600;
		}).thenApplyAsync((res) -> {
			SmallTool.printTimeAndThread("計算500+600+600開始...");
			SmallTool.sleepMillis(300);
			return res+600;
		});
		
		SmallTool.sleepMillis(600);
		
		
		SmallTool.printTimeAndThread(String.format("計算結果%d", completableFuture.join()));		
	}

}
