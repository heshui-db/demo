package com.dongbin.concurrency;

public class TestException implements Runnable {

    @Override
    public void run(){
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new TestException(),"Test_Sleep");
        thread.setDefaultUncaughtExceptionHandler(new ThreadExceptionHandler());
        thread.start();
    }
}
