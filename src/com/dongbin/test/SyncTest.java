package com.dongbin.test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by dongbin on 2018/3/5.
 */
public class SyncTest {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Thread[] threads = new Thread[5];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new RunTest1(countDownLatch,i+1));
            threads[i].start();
        }

        Thread.sleep(1000);
        countDownLatch.countDown();
    }

}

class RunTest implements Runnable {

    private CountDownLatch countDownLatch;
    private int index;

    public RunTest(CountDownLatch countDownLatch,int index) {
        this.countDownLatch = countDownLatch;
        this.index = index;
    }

    @Override
    public void run() {
        try {
            countDownLatch.await();
            get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void get() {
        System.out.println("this is get method"+index);
        set();
    }

    public synchronized void set() {
        System.out.println("this is set method"+index);
    }
}

class RunTest1 implements Runnable{
    private ReentrantLock reentrantLock = new ReentrantLock();

    private CountDownLatch countDownLatch;
    private int index;

    public RunTest1(CountDownLatch countDownLatch,int index) {
        this.countDownLatch = countDownLatch;
        this.index = index;
    }

    @Override
    public void run() {
        try {
            countDownLatch.await();
            get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public  void get() {
        reentrantLock.lock();
        System.out.println("this is get method"+index);
        set();
        reentrantLock.unlock();
    }

    public  void set() {
        reentrantLock.lock();
        System.out.println("this is set method"+index);
        reentrantLock.unlock();
    }
}

