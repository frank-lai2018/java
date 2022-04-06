package thread.future.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.stream.IntStream;

import org.junit.Test;

/**
 * thenCombine:第一個參數是另一個併行的執行緒，第2個參數會等待2條執行緒執行完，拿到結果再去執行
 */
public class ThenCombineTest {

	@Test
	public void test1() {
		SmallTool.printTimeAndThread("小白進入餐廳");
		SmallTool.printTimeAndThread("小白點了 番茄炒蛋 + 一碗米飯");

		CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
			SmallTool.printTimeAndThread("廚師炒菜");
			SmallTool.sleepMillis(200);
			return "番茄炒蛋";
		}).thenCombine(CompletableFuture.supplyAsync(() -> {
			SmallTool.printTimeAndThread("服務員蒸飯");
			SmallTool.sleepMillis(300);
			return "米飯";
		}), (dish, rice) -> {
			SmallTool.printTimeAndThread("服務員打飯");
			SmallTool.sleepMillis(100);
			return String.format("%s + %s 好了", dish, rice);
		});

		SmallTool.printTimeAndThread("小白在打王者");
		SmallTool.printTimeAndThread(String.format("%s ,小白開吃", cf1.join()));

	}

	@Test
	public void test2() {
		SmallTool.printTimeAndThread("計算1+2..+100");

		CompletableFuture<Integer> cf1 = CompletableFuture.supplyAsync(() -> {
			SmallTool.printTimeAndThread("計算1~20");
			SmallTool.sleepMillis(200);
			return IntStream.range(1, 21).sum();
		})
			.thenCombine(CompletableFuture.supplyAsync(() -> {
				SmallTool.printTimeAndThread("計算21~40");
				SmallTool.sleepMillis(200);
				return IntStream.range(21, 41).sum();
			}), (a, b) -> a + b)
			.thenCombine(CompletableFuture.supplyAsync(() -> {
				SmallTool.printTimeAndThread("計算41~60");
				SmallTool.sleepMillis(200);
				return IntStream.range(41, 61).sum();
			}), (a, b) -> a + b)
			.thenCombine(CompletableFuture.supplyAsync(() -> {
				SmallTool.printTimeAndThread("計算61~80");
				SmallTool.sleepMillis(200);
				return IntStream.range(61, 81).sum();
			}), (a, b) -> a + b)
			.thenCombine(CompletableFuture.supplyAsync(() -> {
				SmallTool.printTimeAndThread("計算81~100");
				SmallTool.sleepMillis(200);
				return IntStream.range(81, 101).sum();
			}), (a, b) -> a + b);

		SmallTool.printTimeAndThread("計算結果");
		SmallTool.printTimeAndThread(String.format("%d", cf1.join()));

	}

	/**
	 * 用 applyAsync 也能實現
	 */
	private static void applyAsync() {
		SmallTool.printTimeAndThread("小白進入餐廳");
		SmallTool.printTimeAndThread("小白點了 番茄炒蛋 + 一碗米飯");

		CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
			SmallTool.printTimeAndThread("廚師炒菜");
			SmallTool.sleepMillis(200);
			return "番茄炒蛋";
		});
		CompletableFuture<String> race = CompletableFuture.supplyAsync(() -> {
			SmallTool.printTimeAndThread("服務員蒸飯");
			SmallTool.sleepMillis(300);
			return "米飯";
		});
		SmallTool.printTimeAndThread("小白在打王者");

		String result = String.format("%s + %s 好了", cf1.join(), race.join());
		SmallTool.printTimeAndThread("服務員打飯");
		SmallTool.sleepMillis(100);

		SmallTool.printTimeAndThread(String.format("%s ,小白開吃", result));
	}
}
