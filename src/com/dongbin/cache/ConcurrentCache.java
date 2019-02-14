package com.dongbin.cache;

import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;


/**
 * 用虚引用来限制回收的时间结点
 * <p>
 * 虚引用GC就回收，软引用FULLGC就回收
 *
 * @param <K>
 * @param <V>
 */
public class ConcurrentCache<K, V> {

    private final int size;

    private final Map<K, V> eden;

    private final Map<K, V> longterm;

    public ConcurrentCache(int size, Map<K, V> eden, Map<K, V> longterm) {
        this.size = size;
        this.eden = new ConcurrentHashMap<>();
        this.longterm = new WeakHashMap<>();
    }

    public V get(K k) {
        V v = eden.get(k);

        if (v == null) {
            synchronized (longterm) {
                v = longterm.get(k);
            }
            if (v != null) {
                eden.put(k, v);
            }
        }
        return v;
    }

    public void put(K k, V v) {
        if (this.eden.size() >= size) {
            synchronized (longterm) {
                this.longterm.putAll(eden);
            }
            this.eden.clear();
        }

        this.eden.put(k, v);
    }
}
