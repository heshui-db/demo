package com.dongbin.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 写一个阉割版的线程池
 * <p>
 * 主要是便于理解
 */
public class CustomThreadPool {

    //放置任务的阻塞队列
    private BlockingQueue<Runnable> blockingQueue;

    //线程池的大小
    private final int poolSize;

    //worker 线程数量
    private List<Thread> workers;

    private ReentrantLock lock = new ReentrantLock();

    public CustomThreadPool(int poolSize) {
        this.blockingQueue = new LinkedBlockingQueue<>();
        this.poolSize = poolSize;
        this.workers = new ArrayList<>();
    }

    public void submitTask(Runnable runnable) {

        boolean create = false;
        lock.lock();
        try {
            if (workers.size() <= poolSize) {
                createWorkerAndAddTask(runnable);
                create = true;
            }
        } finally {
            lock.unlock();
        }

        if (!create) {
            try {
                blockingQueue.put(runnable);
            } catch (InterruptedException e) {
                //e.printStackTrace();
            }
        }
    }

    private void createWorkerAndAddTask(Runnable runnable) {
        Thread thread = new Thread(new Worker(runnable));
        thread.start();
        workers.add(thread);
    }

    /**
     * worker线程
     */
    private class Worker implements Runnable {

        private Runnable task;

        private boolean stop = false;

        public Worker(Runnable task) {
            this.task = task;
        }

        @Override
        public void run() {
            while (!stop) {
                try {
                    task.run();
                    task = blockingQueue.take();
                } catch (InterruptedException e) {
                    stop = true;
                }
            }

            System.out.println("开始停止线程:" + Thread.currentThread().getName());
        }
    }

    /**
     * 关闭方法
     */
    public void shutdown() {
        for (Thread thread : workers) {
            thread.interrupt();
        }
    }
}
