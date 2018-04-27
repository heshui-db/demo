package com.dongbin.lock;

import com.sun.tools.javac.util.Assert;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Created by dongbin on 2018/4/20.
 */
public class TwinsLock implements Lock {

    private static final class Sync extends AbstractQueuedSynchronizer {

        Sync(int count) {
            if (count != 2) {
                throw new IllegalMonitorStateException("state 只能为2");
            }
            setState(count);
        }

        @Override
        protected boolean tryReleaseShared(int arg) {
            Assert.check(arg == 1);
            for (; ; ) {
                int current = getState();
                int newCount = current + arg;
                Assert.check(newCount <= 2);
                if (compareAndSetState(current, newCount)) {
                    return true;
                }
            }
        }

        @Override
        protected int tryAcquireShared(int arg) {
            Assert.check(arg == 1);
            for (; ; ) {
                int current = getState();
                int newCount = current - arg;
                if (newCount < 0 || compareAndSetState(current, newCount)) {
                    return newCount;
                }
            }
        }

        final Condition newConditionObject() {
            return new ConditionObject();
        }
    }

    private Sync sync = new Sync(2);

    @Override
    public void lock() {
        sync.acquireShared(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquireShared(1) >= 0;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireSharedNanos(1, unit.toNanos(time));
    }

    @Override
    public void unlock() {
        sync.release(1);
    }

    @Override
    public Condition newCondition() {
        return sync.newConditionObject();
    }
}
