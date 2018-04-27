package com.dongbin.sync;

/**
 * Created by dongbin on 2018/2/8.
 */
public class SynchronizedTest {

    private static String a = "abc";

    public static void main(String[] args) {
        SynchronizedTest object1 = new SynchronizedTest();
        SynchronizedTest object2 = new SynchronizedTest();

        Thread t1 = new Thread(() -> {
            object1.syncMethod1();
        });
        Thread t2 = new Thread(() -> {
            object2.noSyncMethod1();
        });

        t1.start();
        t2.start();
    }

    public synchronized void syncMethod1() {
        a = a.substring(0);
        System.out.println("进入同步method1");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("离开同步method1");
    }

    public synchronized void syncMethod2() {
        System.out.println("进入同步method2");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("离开同步method2");
    }

    public void noSyncMethod() {
        System.out.println("进入 noSyncMethod");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("离开 noSyncMethod");
    }

    public static synchronized void noSyncMethod1() {
        a = a.substring(0);
        System.out.println("进入 noSyncMethod");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("离开 noSyncMethod");
    }
}
