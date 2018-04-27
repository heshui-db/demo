package com.dongbin.sync;

import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by dongbin on 2018/1/24.
 */
public class MainTest extends ReentrantLock{

    public static void main(String[] args) {


        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 200, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(5));
        while (true) {
            for (Map.Entry<String, ReentrantLock> entry : Teanant.lockMap.entrySet()) {
                ReentrantLock lock = entry.getValue();
                if (!lock.isLocked() && Teanant.getTenantTask(entry.getKey())) {
                    Task task = new Task(entry.getKey(), lock);
                    lock.hasQueuedThreads();
                    lock.lock();
                    executor.execute(task);
                }
            }
        }
    }

}
