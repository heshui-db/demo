package com.dongbin.test;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by dongbin on 2018/3/7.
 */
public class CompletionServiceTest {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(11);
        final BlockingQueue<Future<Integer>> blockingQueue = new LinkedBlockingQueue<>(10);
        CompletionService<Integer> completionService = new ExecutorCompletionService<>(executorService, blockingQueue);

        for (int i = 0; i < 10; i++) {
            completionService.submit(() -> {
                int ran = new Random().nextInt(1000);
                Thread.sleep(ran);
                System.out.println(Thread.currentThread().getName() + " 睡眠时间：" + ran);
                return ran;
            });
        }

        for (int i = 0; i < 10; i++) {
            Future<Integer> future = completionService.take();
            System.out.println(future.get());
        }

        executorService.shutdown();
    }
}
