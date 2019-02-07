package com.dongbin.threadlocal.demo;

public class ThreadLocalUser {

    private  int num;

    private MyThreadLocal<MyValue> value = new MyThreadLocal<>();

    public ThreadLocalUser() {
        this(0);
    }

    public ThreadLocalUser(int num) {
        this.num = num;
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("ThreadLocalUser.finalize()");
        super.finalize();
    }

    public void setValue(MyValue value) {
        this.value.set(value);
    }

    public void clear() {
        value.remove();
    }
}
