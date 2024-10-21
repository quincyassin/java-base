package org.ricardo.base.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableFuture {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<String> callable = () -> {
            System.out.println("start");
            Thread.sleep(2000);
            return "Hello World";
        };

        FutureTask<String> futureTask = new FutureTask<>(callable);

        new Thread(futureTask).start();

        System.out.println("取消: " + futureTask.isCancelled());
        System.out.println(futureTask.cancel(true));
        System.out.println("取消: " + futureTask.isCancelled());

        for (int i = 0; i < 3; i++) {
            Thread.sleep(1000);
            System.out.println("完成: " + futureTask.isDone());
        }

//        System.out.println(futureTask.get());
    }
}
