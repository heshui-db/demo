package com.dongbin.threadlocal.demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {

    public static void main(String[] args) throws InterruptedException {
        Test test = new Test();
        test.test1();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void test1() throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(1);
        MyThread thread = new MyThread(() -> {
            ThreadLocalUser user = new ThreadLocalUser();
            MyValue value = new MyValue(1);
            user.setValue(value);
        });
        service.execute(thread);

        Thread.sleep(100);


        collectGarbage();
    }

    private void collectGarbage() {
        for (int i = 0; i < 10; i++) {
            System.gc();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
