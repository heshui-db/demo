package com.dongbin.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 缓存管理器
 *
 * @param <T>
 */
public class MyCacheManager<T> {

    private Map<String, T> cache = new ConcurrentHashMap<>();

    public T get(String key) {
        return cache.get(key);
    }

    public void put(String key, T t) {
        cache.put(key, t);
    }

    public void evictCache(String key) {
        cache.remove(key);
    }

    public void evictCache() {
        cache.clear();
    }

}
