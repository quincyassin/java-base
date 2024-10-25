package org.ricardo.base.synchronizeds;

public class SynchronizedClassInfo {

    Object object = new Object();

    public void get() {
        synchronized (object) {

        }
    }
}
