package org.ricardo.base.executor.policy;

import java.util.concurrent.*;

public class ExecutorPolicy {

    public static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
            2,
            4,
            5000,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(10),
//                new ThreadPoolExecutor.AbortPolicy()
//            new ThreadPoolExecutor.CallerRunsPolicy()
//            new ThreadPoolExecutor.DiscardOldestPolicy()
            new ThreadPoolExecutor.DiscardPolicy()
    );

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            Runnable runnable = () -> {
                try {
                    System.out.println(Thread.currentThread() + " - 开始执行任务");
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            };
            threadPoolExecutor.submit(runnable);
        }
        System.out.println("执行完毕");
    }
}
