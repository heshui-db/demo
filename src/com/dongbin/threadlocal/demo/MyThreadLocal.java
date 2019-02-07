package com.dongbin.threadlocal.demo;

public class MyThreadLocal<T> extends ThreadLocal<T> {

    @Override
    protected void finalize() throws Throwable {
        System.out.println("MyThreadLocal.finalize()");
        super.finalize();
    }
}
