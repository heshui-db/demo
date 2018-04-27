package com.dongbin.atomic;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * Created by dongbin on 2018/3/2.
 */
public class AtomicIntegerArrayTest {

    private final static AtomicIntegerArray ATOMIC_INTEGER_ARRAY = new AtomicIntegerArray(10);

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[100];
        for (int i = 0; i < threads.length; i++) {
            final int index = i % 10;
            final int threadNum = i;

            threads[i] = new Thread(() -> {
                int result = ATOMIC_INTEGER_ARRAY.addAndGet(index, index + 1);
                System.out.println("线程编号：" + threadNum + ",对应原始值：" + index + ",result:" + result);
            });
            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }
        System.out.println("==============执行完毕！==============");

        for(int i=0;i<ATOMIC_INTEGER_ARRAY.length();i++){
            System.out.println(ATOMIC_INTEGER_ARRAY.get(i));
        }
    }
}
