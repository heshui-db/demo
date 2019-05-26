package com.dongbin.db20190526;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class ConcurrentHashSet<T> extends AbstractSet<T> {

    private Map<T, Object> map = new ConcurrentHashMap<>();
    private final Object PRESENT = new Object();

    private AtomicInteger count = new AtomicInteger();

    @Override
    public Iterator<T> iterator() {
        return map.keySet().iterator();
    }

    @Override
    public int size() {
        return count.get();
    }

    @Override
    public boolean add(T t) {
        count.incrementAndGet();
        return map.put(t, PRESENT) == null;
    }

    @Override
    public boolean remove(Object o) {
        count.decrementAndGet();
        return map.remove(o) == PRESENT;
    }
}
