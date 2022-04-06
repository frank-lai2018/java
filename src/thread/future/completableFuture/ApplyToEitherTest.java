package thread.future.completableFuture;

import java.util.concurrent.CompletableFuture;

/**
 * applyToEither:用於等待兩條執行緒誰先執行完就用誰的結果
 * */
public class ApplyToEitherTest {
	public static void main(String[] args) {
        SmallTool.printTimeAndThread("張三走出餐廳，來到公交站");
        SmallTool.printTimeAndThread("等待 700路 或者 800路 公交到來");

        CompletableFuture<String> bus = CompletableFuture.supplyAsync(() -> {
            SmallTool.printTimeAndThread("700路公交正在趕來");
            SmallTool.sleepMillis(3100);
            return "700路到了";
        }).applyToEither(CompletableFuture.supplyAsync(() -> {
            SmallTool.printTimeAndThread("800路公交正在趕來");
            SmallTool.sleepMillis(200);
            return "800路到了";
        }), firstComeBus -> firstComeBus);

        SmallTool.printTimeAndThread(String.format("%s,小白坐車回家", bus.join()));
    }
}
