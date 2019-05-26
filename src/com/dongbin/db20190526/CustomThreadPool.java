package com.dongbin.db20190526;

import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class CustomThreadPool {

    private ReentrantLock lock = new ReentrantLock();

    private volatile int minSize;
    private volatile int maxSize;

    private long keepAliveTime;
    private TimeUnit unit;

    private BlockingQueue<Runnable> workQueue;

    private volatile Set<Worker> workers;

    private AtomicBoolean isShutdown = new AtomicBoolean(false);
    private AtomicInteger totalTask = new AtomicInteger();

    private Object shutDownNotify = new Object();

    private Notify notify;


    public CustomThreadPool(int minSize, int maxSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, Notify notify) {
        this.minSize = minSize;
        this.maxSize = maxSize;
        this.keepAliveTime = keepAliveTime;
        this.unit = unit;
        this.workQueue = workQueue;
        this.notify = notify;

        workers = new ConcurrentHashSet<>();
    }

    public void execute(Runnable runnable) {
        if (runnable == null) {
            throw new NullPointerException("Runnable NullPointerException");
        }

        if (isShutdown.get()) {
            System.out.println("线程池已经关闭，不能添加任务");
            return;
        }

        totalTask.incrementAndGet();
        if (workers.size() < minSize) {
            addWorker(runnable);
            return;
        }

        boolean offer = workQueue.offer(runnable);
        if (!offer) {
            if (workers.size() < maxSize) {
                addWorker(runnable);
                return;
            }

            System.out.println("超过最大线程数");
            try {
                //阻塞
                workQueue.put(runnable);
            } catch (InterruptedException e) {
                System.out.println("workQueue 被中断");
            }
        }
    }

    private void addWorker(Runnable runnable) {
        Worker worker = new Worker(runnable, true);
        worker.startTask();
        workers.add(worker);
    }

    private final class Worker extends Thread {
        private Runnable task;
        private Thread thread;

        private boolean isNewTask;

        public Worker(Runnable task, boolean isNewTask) {
            this.task = task;
            this.isNewTask = isNewTask;
            thread = this;
        }

        public void startTask() {
            thread.start();
        }

        public void close() {
            thread.interrupt();
        }

        @Override
        public void run() {
            Runnable task = null;
            if (isNewTask) {
                task = this.task;
            }

            try {
                while (task != null || (task = getTask()) != null) {
                    try {
                        task.run();
                    } finally {
                        task = null;
                        int number = totalTask.decrementAndGet();
                        if (number == 0) {
                            synchronized (shutDownNotify) {
                                shutDownNotify.notify();
                            }
                        }
                    }
                }
            } finally {
                workers.remove(this);
                tryClose(true);
            }
        }
    }

    private Runnable getTask() {
        if (isShutdown.get() && totalTask.get() == 0) {
            return null;
        }

        lock.lock();
        try {
            Runnable task;
            if (workers.size() > minSize) {
                task = workQueue.poll(keepAliveTime, unit);
            } else {
                task = workQueue.take();
            }
            if (task != null) {
                return task;
            }
        } catch (InterruptedException e) {
            return null;
        } finally {
            lock.unlock();
        }

        return null;
    }

    public void shutdown() {
        System.out.println("关闭线程池");
        isShutdown.set(true);
        tryClose(true);
    }

    public void shutdownNow() {
        System.out.println("关闭线程池");
        isShutdown.set(true);
        tryClose(false);
    }

    private void tryClose(boolean isTry) {
        if (!isTry) {
            closeAllTask();
        } else {
            if (isShutdown.get() && totalTask.get() == 0) {
                closeAllTask();
            }
        }
    }

    private void closeAllTask() {
        for (Worker worker : workers) {
            worker.close();
        }
    }


    public int getWorkCount() {
        return this.workers.size();
    }

    public void mainNotify() {
        synchronized (shutDownNotify) {
            while (totalTask.get() > 0) {
                try {
                    shutDownNotify.wait();
                    if (notify != null) {
                        notify.notifyListen();
                    }
                } catch (InterruptedException e) {
                    return;
                }
            }
        }
    }

}
