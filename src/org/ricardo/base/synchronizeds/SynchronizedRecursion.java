package org.ricardo.base.synchronizeds;

public class SynchronizedRecursion {

    public synchronized void method1() {
        System.out.println(1);
        method2();
    }

    public static synchronized void method2() {
        System.out.println(2);
    }

    public static void main(String[] args) {
        SynchronizedRecursion method1 = new SynchronizedRecursion();
        method1.method1();
    }
}
