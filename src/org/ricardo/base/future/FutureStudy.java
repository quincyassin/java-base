package org.ricardo.base.future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureStudy {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Runnable runnable = () -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Hello World");
        };

        FutureTask<String> futureTask = new FutureTask<>(runnable, "执行完成");

        Thread thread = new Thread(futureTask);
        thread.start();
        System.out.println(futureTask.get());
    }
}
