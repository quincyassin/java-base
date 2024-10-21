package org.ricardo.base.completablefuture;

import java.util.concurrent.*;

public class CompletableFutureStudy {

    private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
            10,
            13,
            10,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<Runnable>(100),
            Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.AbortPolicy()
    );

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture.runAsync(() -> {
            System.out.println("hello starting");
            int i = 100 / 50;
            System.out.println("bye ending");
        }, threadPoolExecutor);

        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("hello starting");
            int i = 100 / 50;
            System.out.println("bye ending");
            return i;
        }, threadPoolExecutor);
        System.out.println(completableFuture.get());

    }
}
