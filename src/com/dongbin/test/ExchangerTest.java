package com.dongbin.test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by dongbin on 2018/3/7.
 */
public class ExchangerTest {

    private static final Exchanger<String> EXCHANGER = new Exchanger<>();
    private static ExecutorService executorService = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        executorService.execute(() -> {
            try {
                countDownLatch.await();
                String A = "银行流水111A";
                System.out.println(EXCHANGER.exchange(A));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        executorService.execute(() -> {
            try {
                countDownLatch.await();
                String B = "银行流水B";
                String A = EXCHANGER.exchange(null);
                System.out.println("A和B数据是否一致：" + A.equals(B) + ",A录入的是：" + A + ",B录入是：" + B);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        countDownLatch.countDown();
        executorService.shutdown();
    }
}
