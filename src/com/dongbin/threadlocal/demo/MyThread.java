package com.dongbin.threadlocal.demo;

public class MyThread extends Thread {

    public MyThread(Runnable target) {
        super(target);
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("MyThread.finalize()");
        super.finalize();
    }
}
