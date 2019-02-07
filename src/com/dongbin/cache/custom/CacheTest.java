package com.dongbin.cache.custom;

public class CacheTest {

    public static void main(String[] args) throws InterruptedException {
        CacheManager cacheManager = CacheManager.getCacheManager();

        Cache cache = new Cache(cacheConfiguration());
        putElement(cache);
        cacheManager.putCache(cache);

        System.out.println(cache.getSize());
        System.out.println(cache.get(2));

        System.out.println(cache.getSize());

        Thread.sleep(3000);

        System.out.println(cache.get(6));
        cache.get(6);

        Thread.sleep(3000);

        System.out.println(cache.getSize());

        Thread.sleep(15000);

        System.out.println(cache.getSize());

    }

    public static CacheConfiguration cacheConfiguration() {
        CacheConfiguration configuration = new CacheConfiguration();
        configuration.setCacheName("test1");
        configuration.setMaxElementsInMemory(5);
        configuration.setTimeToIdleSeconds(5);
        configuration.setTimeToLiveSeconds(15);
        configuration.setDiskExpiryThreadIntervalSeconds(5);
//        configuration.setEternal(true);
        configuration.setNeedCacheCheckListener(true);
        return configuration;
    }

    public static void putElement(Cache cache) {
        cache.put(new Element(1, 2));
        cache.get(1);
        cache.put(new Element(2, 2));
        cache.put(new Element(3, 2));
        cache.get(3);
        cache.put(new Element(4, 2));
        cache.put(new Element(5, 2));
        cache.get(5);
        cache.put(new Element(6, 2));
        cache.get(6);
        cache.put(new Element(7, 2));
    }
}
