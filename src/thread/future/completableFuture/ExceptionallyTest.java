package thread.future.completableFuture;

import java.util.concurrent.CompletableFuture;


/**
 * exceptionally:可以接收異常
 * */
public class ExceptionallyTest {
	public static void main(String[] args) {
        SmallTool.printTimeAndThread("張三走出餐廳，來到公交站");
        SmallTool.printTimeAndThread("等待 700路 或者 800路 公交到來");

        CompletableFuture<String> bus = CompletableFuture.supplyAsync(() -> {
            SmallTool.printTimeAndThread("700路公交正在趕來");
            SmallTool.sleepMillis(100);
            return "700路到了";
        }).applyToEither(CompletableFuture.supplyAsync(() -> {
            SmallTool.printTimeAndThread("800路公交正在趕來");
            SmallTool.sleepMillis(200);
            return "800路到了";
        }), firstComeBus -> {
            SmallTool.printTimeAndThread(firstComeBus);
            if (firstComeBus.startsWith("700")) {
                throw new RuntimeException("撞樹了……");
            }
            return firstComeBus;
        }).exceptionally(e -> {
            SmallTool.printTimeAndThread(e.getMessage());
            SmallTool.printTimeAndThread("小白叫出租車");
            return "出租車 叫到了";
        });

        SmallTool.printTimeAndThread(String.format("%s,小白坐車回家", bus.join()));
    }
}
