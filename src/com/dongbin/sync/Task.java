package com.dongbin.sync;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by dongbin on 2018/1/24.
 */
public class Task implements Runnable {

    private String tenantId;

    private ReentrantLock reentrantLock;

    public Task(String tenantId, ReentrantLock reentrantLock) {
        this.tenantId = tenantId;
        this.reentrantLock = reentrantLock;
    }

    @Override
    public void run() {
        System.out.println("tenantId is:" + tenantId);
        try {
            Thread.sleep(1000);
            reentrantLock.unlock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
