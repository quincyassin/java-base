package org.ricardo.base.synchronizeds;

/**
 * 类锁：
 * 一个class可能有多个对象，但是只有一个class对象
 * 形式一：synchronized 加在static方法上
 * 形式二：synchronized (*.class) 代码块
 */
public class SynchronizedStaticMethod implements Runnable{

    public static SynchronizedStaticMethod instance1 = new SynchronizedStaticMethod();
    public static SynchronizedStaticMethod instance2 = new SynchronizedStaticMethod();

    @Override
    public void run() {
        method();
        method1();
    }

    /**
     * 不加static synchronized修饰方法的时候默认是this
     * 加static synchronized修饰方法的时候是.class
     */
    public static synchronized void method() {
        System.out.println(Thread.currentThread().getName() + " is running");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName() + " is stopped");
    }

    public void method1() {
        synchronized (SynchronizedStaticMethod.class) {
            System.out.println(Thread.currentThread().getName() + " is running");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + " is stopped");
        }
    }

    public static void main(String[] args) {
        Thread thread0 = new Thread(instance1);
        Thread thread1 = new Thread(instance2);
        thread0.start();
        thread1.start();
        while (thread0.isAlive() || thread1.isAlive()) {

        }
        System.out.println("finish");
    }
}
