package com.dongbin.test;

import java.math.BigDecimal;

/**
 * Created by dongbin on 2017/10/10.
 */
public class ThreadTest {

    public static void main(String[] args) {

        BigDecimal decimal1 = new BigDecimal("0");
        BigDecimal decimal = new BigDecimal("0");
        BigDecimal decimal2 = new BigDecimal("1");
        decimal1.add(decimal);
        System.out.println("sss");

    }

    public static void getBig(BigDecimal big) {
        big = big.add(new BigDecimal(2));
    }

}

class Task implements Runnable {

    @Override
    public void run() {
        Thread.currentThread().setUncaughtExceptionHandler(new ExceptionHandler());
        while (true) {
            System.out.println(3 / 2);
            System.out.println(3 / 0);
            System.out.println(3 / 1);
        }
    }

}

class ExceptionHandler implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("Exception:" + e.getMessage());
        System.out.println(t.isInterrupted());
        new Thread(new Task()).start();
    }
}
