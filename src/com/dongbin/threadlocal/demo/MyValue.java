package com.dongbin.threadlocal.demo;

public class MyValue {

    private final int value;

    public MyValue(int value) {
        this.value = value;
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("MyValue.finalize();value:" + value);
        super.finalize();
    }
}
