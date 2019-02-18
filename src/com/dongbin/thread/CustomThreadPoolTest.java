package com.dongbin.thread;

public class CustomThreadPoolTest {

    public static void main(String[] args) throws InterruptedException {
        CustomThreadPool customThreadPool = new CustomThreadPool(3);

        try {
            for (int i = 0; i < 5; i++) {
                customThreadPool.submitTask(() -> {
                    int sleep = (int) (Math.random() * (2) * 1000);
                    try {
                        Thread.sleep(sleep);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + ":sleep[" + sleep + "]");
                });
            }
            customThreadPool.shutdown();
            Thread.sleep(10000);
        } finally {

        }


    }
}
