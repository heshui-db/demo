package com.dongbin.db20190520;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class LimitStreamMap {
    private final static Map<Long, AtomicLong> limitMap = new ConcurrentHashMap<>();

    private int limit;

    private long threshold;

    public LimitStreamMap(int limit, long threshold, TimeUnit timeUnit) {
        this.limit = limit;
        this.threshold = threshold;
    }

    public LimitStreamMap() {
        this(10, 1000, TimeUnit.MINUTES);
    }

    public boolean isLimit() {
        long current = System.currentTimeMillis() / this.threshold;
        AtomicLong atomicLong = limitMap.get(current);
        if (atomicLong == null) {
            atomicLong = new AtomicLong();
            limitMap.remove(current - 1);
        }
        return atomicLong.incrementAndGet() > limit;
    }

}
