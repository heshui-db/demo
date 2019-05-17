package com.dongbin;


import java.util.concurrent.Semaphore;

public class Demo {

    private Demo() {
        System.out.println("init");
    }

    private static void test() {
        System.out.println("test");
    }

    public static void main(String[] args) throws Exception {
        Semaphore semaphore = new Semaphore(5);
        semaphore.release();
        semaphore.release();
        semaphore.acquire();
    }
}
