package org.ricardo.base.synchronizeds;

public class SynchronizedObject implements Runnable {

    public static SynchronizedObject instance = new SynchronizedObject();

    final Object lock1 = new Object();

    final Object lock2 = new Object();

    @Override
    public void run() {
        synchronized (lock1) {
            System.out.println(Thread.currentThread().getName() + " is running");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + " is stopped");
        }

        synchronized (lock2) {
            System.out.println(Thread.currentThread().getName() + " is running lock2");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + " is stopped lock2");
        }
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
