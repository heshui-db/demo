package com.dongbin.cache.custom;

public class CacheConfiguration {

    private String cacheName;

    //是否需要检查
    private Boolean isNeedCacheCheckListener = false;


    public Boolean getNeedCacheCheckListener() {
        return isNeedCacheCheckListener;
    }

    public void setNeedCacheCheckListener(Boolean needCacheCheckListener) {
        isNeedCacheCheckListener = needCacheCheckListener;
    }

    private Integer maxElementsInMemory;

    //缓存是否永久有效，设置为true  过期时间无效
    private boolean eternal = false;

    /**
     * 缓存失效前 允许闲置时间，0表示闲置时间无穷大
     * 仅当timeToLiveSeconds!=0有效
     */
    private Integer timeToIdleSeconds = 0;

    /**
     * 允许缓存存活时间，介于创建时间到失效时间
     * 0 表示无穷大
     */
    private Integer timeToLiveSeconds = 0;

    // 对象检测线程运行的时间间隔。表示对象状态的线程多长时间运行一次
    // 这里暂时用来对内存对象的检查
    private Integer diskExpiryThreadIntervalSeconds = 120;


    // 如果缓存满了，执行清空策略
    // 可选FIFO,LFU 这里要用枚举类型
    // FIFO ：先进先出
    // LFU:最少使用,一直以来最少被使用的，缓存缓存有一个hit属性，清除hit最小的
    // LRU:最近最少使用，缓存元素有个时间戳，当缓存容量满了，而又需要腾出新地方
    // 来缓存的时候，那么现有的缓存缓存中时间戳离当前时间最远的缓存将被清除缓存
    private String memoryStoreEvictionPolicy = EvictionType.LRU.name();

    // 暂时未用
    // 当缓存数量达到最大值时，允许将缓存写入到磁盘
    private Boolean overflowToDisk = false;

    /**
     * 磁盘存储的最大元素个数
     */
    private Integer maxElementsOnDisk = 0;

    /**
     * 是否持久化磁盘
     */
    private Boolean diskPersistent = false;


    public CacheConfiguration() {
    }

    public CacheConfiguration(String cacheName, Boolean isNeedCacheCheckListener, Integer maxElementsInMemory, boolean eternal, Integer timeToIdleSeconds, Integer timeToLiveSeconds, Integer diskExpiryThreadIntervalSeconds) {
        this.cacheName = cacheName;
        this.isNeedCacheCheckListener = isNeedCacheCheckListener;
        this.maxElementsInMemory = maxElementsInMemory;
        this.eternal = eternal;
        this.timeToIdleSeconds = timeToIdleSeconds;
        this.timeToLiveSeconds = timeToLiveSeconds;
        this.diskExpiryThreadIntervalSeconds = diskExpiryThreadIntervalSeconds;
    }

    public String getCacheName() {
        return cacheName;
    }

    public void setCacheName(String cacheName) {
        this.cacheName = cacheName;
    }

    public Integer getMaxElementsInMemory() {
        return maxElementsInMemory;
    }

    public void setMaxElementsInMemory(Integer maxElementsInMemory) {
        this.maxElementsInMemory = maxElementsInMemory;
    }

    public boolean isEternal() {
        return eternal;
    }

    public void setEternal(boolean eternal) {
        this.eternal = eternal;
    }

    public Integer getTimeToIdleSeconds() {
        return timeToIdleSeconds;
    }

    public void setTimeToIdleSeconds(Integer timeToIdleSeconds) {
        this.timeToIdleSeconds = timeToIdleSeconds;
    }

    public Integer getTimeToLiveSeconds() {
        return timeToLiveSeconds;
    }

    public void setTimeToLiveSeconds(Integer timeToLiveSeconds) {
        this.timeToLiveSeconds = timeToLiveSeconds;
    }

    public Integer getDiskExpiryThreadIntervalSeconds() {
        return diskExpiryThreadIntervalSeconds;
    }

    public void setDiskExpiryThreadIntervalSeconds(Integer diskExpiryThreadIntervalSeconds) {
        this.diskExpiryThreadIntervalSeconds = diskExpiryThreadIntervalSeconds;
    }

    public String getMemoryStoreEvictionPolicy() {
        return memoryStoreEvictionPolicy;
    }

    public void setMemoryStoreEvictionPolicy(String memoryStoreEvictionPolicy) {
        this.memoryStoreEvictionPolicy = memoryStoreEvictionPolicy;
    }

    public Boolean getOverflowToDisk() {
        return overflowToDisk;
    }

    public void setOverflowToDisk(Boolean overflowToDisk) {
        this.overflowToDisk = overflowToDisk;
    }

    public Integer getMaxElementsOnDisk() {
        return maxElementsOnDisk;
    }

    public void setMaxElementsOnDisk(Integer maxElementsOnDisk) {
        this.maxElementsOnDisk = maxElementsOnDisk;
    }

    public Boolean getDiskPersistent() {
        return diskPersistent;
    }

    public void setDiskPersistent(Boolean diskPersistent) {
        this.diskPersistent = diskPersistent;
    }
}
