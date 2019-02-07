package com.dongbin.algorithm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class LRULinkedHashMap<K, V> extends LinkedHashMap<K, V> {

    //最大容量
    private final int maxCapcatity;

    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    private final ReentrantLock reentrantLock = new ReentrantLock();

    public LRULinkedHashMap(int initialCapacity, int maxCapcatity) {
        super(initialCapacity, DEFAULT_LOAD_FACTOR, true);
        this.maxCapcatity = maxCapcatity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > maxCapcatity;
    }

    @Override
    public V get(Object key) {
        try {
            reentrantLock.lock();
            return super.get(key);
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override
    public void clear() {
        try {
            reentrantLock.lock();
            super.clear();
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override
    public int size() {
        try {
            reentrantLock.lock();
            return super.size();
        } finally {
            reentrantLock.unlock();
        }
    }


    @Override
    public boolean containsKey(Object key) {
        try {
            reentrantLock.lock();
            return super.containsKey(key);
        } finally {
            reentrantLock.unlock();
        }

    }

    @Override
    public V put(K key, V value) {
        try {
            reentrantLock.lock();
            return super.put(key, value);
        } finally {
            reentrantLock.unlock();
        }
    }

    public Collection<Map.Entry<K, V>> getAll() {
        try {
            reentrantLock.lock();
            return new ArrayList<>(super.entrySet());
        } finally {
            reentrantLock.unlock();
        }
    }
}
