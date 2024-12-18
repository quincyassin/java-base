package org.ricardo.base.sort;

public class Test2 implements Runnable{
    @Override
    public void run() {
        System.out.println(this);
    }

    public static void main(String[] args) {
        Test3 test3 = new Test3() {
            @Override
            public void get() {
                System.out.println("我是谁" + this);
                super.get();
            }
        };
        test3.get();
        System.out.println("我是谁" + test3);
    }
}
