package com.dongbin.cache.custom;

public class Cache extends MemoryCache {

    private CacheConfiguration cacheConfiguration;

    private CacheListener cacheListener;

    public Cache(CacheConfiguration cacheConfiguration) {
        super(cacheConfiguration);
        this.cacheConfiguration = cacheConfiguration;
        if (!cacheConfiguration.isEternal() && cacheConfiguration.getNeedCacheCheckListener()) {
            cacheListener = new CacheListener(this);
            cacheListener.start();
        }
    }


    public CacheConfiguration getCacheConfiguration() {
        return cacheConfiguration;
    }

    public void destroy() {
        try {
            super.clear();
            if (cacheListener != null) {
                cacheListener.interrupt();
                cacheListener.destory();
            }
        } catch (Exception e) {

        }
    }
}
