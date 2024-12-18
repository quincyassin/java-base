package org.ricardo.base.sort;

public class Test {

    public static void main(String[] args) {
        Test test = new Test();
        test.test();
    }

    public void test() {
        Runnable runnable = () -> {
            synchronized (Test.this) {
                try {
                    System.out.println("锁住了");
                    Test.this.wait();
                    System.out.println("我免费了");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        new Thread(runnable).start();
        try {
            Thread.sleep(3000);
            synchronized (this) {
                this.notify();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
