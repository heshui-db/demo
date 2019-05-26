package com.dongbin.db20190526;

import com.dongbin.sync.Task;
import javafx.concurrent.Worker;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {

        Notify notify = () -> System.out.println("hello");
        BlockingQueue queue = new ArrayBlockingQueue(4);
        CustomThreadPool pool = new CustomThreadPool(3, 5, 1, TimeUnit.SECONDS, queue, notify);
        for (int i = 0; i < 10; i++) {
            pool.execute(new Task(i + ""));
        }


        //等线程执行完成
        pool.mainNotify();
        pool.shutdown();

    }

    private static class Task implements Runnable {
        private String name;

        public Task(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + ":" + name);
                Thread.sleep(1000);
            } catch (Exception e) {
                //nothing
            }
        }
    }
}
