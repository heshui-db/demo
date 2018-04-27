package com.dongbin.lock;


import java.util.concurrent.CountDownLatch;

/**
 * Created by dongbin on 2018/4/20.
 */
public class Main {

    public static void main(String[] args) {
        final CountDownLatch countDownLatch1 = new CountDownLatch(1);
        final CountDownLatch countDownLatch2 = new CountDownLatch(50);
        Mutex mutex = new Mutex();
        Thread[] threads = new Thread[50];
        final Bean bean1 = new Bean();
        final Bean bean2 = new Bean();
        for (int i = 0; i < 50; i++) {
            threads[i] = new Thread(() -> {
                try {
                    countDownLatch1.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                bean1.setIndex(bean1.getIndex() + 1);
                mutex.lock();
                bean2.setIndex(bean2.getIndex() + 1);
                mutex.unlock();
                countDownLatch2.countDown();
            });

            threads[i].start();
        }

        countDownLatch1.countDown();
        try {
            countDownLatch2.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(bean1.getIndex() + ":" + bean2.getIndex());
    }
}

