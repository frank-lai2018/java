package thread.future.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * threadPool應用，work模擬耗時操作
 * */
public class ThreadPoolTest {

    public static void main(String[] args) {
//        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        ExecutorService threadPool = getThreadPoolExecutor();

        long startTime = System.currentTimeMillis();

        CompletableFuture[] dishes = IntStream.range(1, 12).boxed()
                .map(i -> CompletableFuture.runAsync(() -> {
                	work(i);
                }, threadPool))
                .toArray(size -> new CompletableFuture[size]);

        CompletableFuture.allOf(dishes).join();

        threadPool.shutdown();

        SmallTool.printTimeAndThread("總執行緒執行完了 " + (System.currentTimeMillis() - startTime));

    }
    
    
    public static void work(int i) {
    	SmallTool.sleepMillis(3000);
    	SmallTool.printTimeAndThread(i+"執行緒執行完了");
    }
    
    public static ThreadPoolExecutor getThreadPoolExecutor() {
    	return new ThreadPoolExecutor(2, 5,  20L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(2));
    }
    
    
}
