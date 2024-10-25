package org.ricardo.base.synchronizeds;

public class SynchronizedMethod implements Runnable {

    public static SynchronizedMethod instance = new SynchronizedMethod();


    @Override
    public void run() {
        method();
    }

    public synchronized void method() {
        System.out.println(Thread.currentThread().getName() + " is running");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName() + " is stopped");
    }

    public static void main(String[] args) {
        Thread thread0 = new Thread(instance);
        Thread thread1 = new Thread(instance);
        thread0.start();
        thread1.start();
        while (thread0.isAlive() || thread1.isAlive()) {

        }
        System.out.println("finish");
    }
}
