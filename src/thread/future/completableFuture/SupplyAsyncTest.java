package thread.future.completableFuture;

import java.util.concurrent.CompletableFuture;

/**
 * supplyAsync:是有結果的的非同步執行方法
 * */
public class SupplyAsyncTest {
	public static void main(String[] args) {
        SmallTool.printTimeAndThread("小白進入餐廳");
        SmallTool.printTimeAndThread("小白點了 番茄炒蛋 + 一碗米飯");

        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            SmallTool.printTimeAndThread("廚師炒菜");
            SmallTool.sleepMillis(200);
            SmallTool.printTimeAndThread("廚師打飯");
            SmallTool.sleepMillis(100);
            return "番茄炒蛋 + 米飯 做好了";
        });

        SmallTool.printTimeAndThread("小白在打王者");
        SmallTool.printTimeAndThread(String.format("%s ,小白開吃", cf1.join()));
    }
}
