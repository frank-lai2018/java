package thread.future.completableFuture;

import java.util.concurrent.CompletableFuture;

/**
 * thenCompose:會等待supplyAsync執行完後，到結果再繼續執行
 * */
public class ThenComposeTest {
	public static void main(String[] args) {
        SmallTool.printTimeAndThread("小白進入餐廳");
        SmallTool.printTimeAndThread("小白點了 番茄炒蛋 + 一碗米飯");

        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            SmallTool.printTimeAndThread("廚師炒菜");
            SmallTool.sleepMillis(200);
            return "番茄炒蛋";
        }).thenCompose(dish -> CompletableFuture.supplyAsync(() -> {
            SmallTool.printTimeAndThread("服務員打飯");
            SmallTool.sleepMillis(100);
            return dish + " + 米飯";
        }));

        SmallTool.printTimeAndThread("小白在打王者");
        SmallTool.printTimeAndThread(String.format("%s 好了,小白開吃", cf1.join()));
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
            CompletableFuture<String> race = CompletableFuture.supplyAsync(() -> {
                SmallTool.printTimeAndThread("服務員打飯");
                SmallTool.sleepMillis(100);
                return " + 米飯";
            });
            return "番茄炒蛋" + race.join();
        });

        SmallTool.printTimeAndThread("小白在打王者");
        SmallTool.printTimeAndThread(String.format("%s 好了,小白開吃", cf1.join()));
    }
}
