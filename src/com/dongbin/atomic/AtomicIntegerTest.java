package com.dongbin.atomic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by dongbin on 2018/3/2.
 */
public class AtomicIntegerTest {

    private final static AtomicInteger TEST_INTEGER = new AtomicInteger(1);

    public static int index = 1;

    public static volatile int  volatile_index = 1;

    public static void main(String[] args) throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(2);
        Thread[] threads = new Thread[10];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int j = 0; j < 100; j++) {
                    volatile_index++;
                    index++;
                    TEST_INTEGER.incrementAndGet();
                }
            }
            );
            threads[i].start();
        }

        Thread.sleep(1000);
        countDownLatch.countDown();

        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("atomic integer:"+TEST_INTEGER.get());
        System.out.println("index:"+index);
        System.out.println("volatile:"+volatile_index);
    }
}
