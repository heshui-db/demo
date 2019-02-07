package com.dongbin.cache.custom;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CacheManager {

    private CacheManager() {
    }

    private static class singleton {
        private static final CacheManager instance = new CacheManager();
    }

    public static CacheManager getCacheManager() {
        return singleton.instance;
    }

    public Map<String, Cache> cacheMap = new HashMap<>();

    public Cache getCache(String cacheName) {
        return cacheMap.get(cacheName);
    }

    public void putCache(Cache cache) {
        if (cache != null && !cacheMap.containsKey(cache.getName())) {
            cacheMap.put(cache.getName(), cache);
        }
    }

    public void remove(String cacheName) {
        Cache cache = cacheMap.remove(cacheName);

        if (cache != null) {
            cache.destroy();
        }

    }

    public void removeAllCache() {
        Set<String> keySet = cacheMap.keySet();
        for (String key : keySet) {
            remove(key);
        }
    }
}
