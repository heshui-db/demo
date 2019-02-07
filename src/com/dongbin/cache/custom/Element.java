package com.dongbin.cache.custom;

import java.io.Serializable;

public class Element implements Serializable {

    static final long ONE_SECOND = 1000L;

    private Object key;

    private Object value;

    //使用次数
    private volatile long hitCount = 0;

    private Boolean isOpen = false;

    private volatile int timeToLive = 0;

    private volatile int timeToIdle = 0;

    private transient long createTime;

    private transient long lastAccessTime;

    private volatile long lastUpdateTime;

    private volatile boolean cacheDefaultLifespan = true;

    public Element(Object key, Object value) {
        init(key, value);
    }


    public Element(Object key, Object value, Boolean isOpen) {
        init(key, value);
        this.isOpen = isOpen;
    }

    private void init(Object key, Object value) {
        this.key = key;
        this.value = value;
        this.createTime = System.currentTimeMillis();
        this.lastAccessTime = System.currentTimeMillis();
    }

    public Object getKey() {
        return key;
    }

    public void setKey(Object key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public long getHitCount() {
        return hitCount;
    }

    public void addHitCount() {
        hitCount += 1;
    }

    public void setHitCount(long hitCount) {
        this.hitCount = hitCount;
    }

    public Boolean getOpen() {
        return isOpen;
    }

    public void setOpen(Boolean open) {
        isOpen = open;
    }

    public int getTimeToLive() {
        return timeToLive;
    }

    public void setTimeToLive(int timeToLive) {
        this.timeToLive = timeToLive;
    }

    public int getTimeToIdle() {
        return timeToIdle;
    }

    public void setTimeToIdle(int timeToIdle) {
        this.timeToIdle = timeToIdle;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getLastAccessTime() {
        return lastAccessTime;
    }

    public void setLastAccessTime(long lastAccessTime) {
        this.lastAccessTime = lastAccessTime;
    }

    public long getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(long lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public boolean isCacheDefaultLifespan() {
        return cacheDefaultLifespan;
    }

    public void setCacheDefaultLifespan(boolean cacheDefaultLifespan) {
        this.cacheDefaultLifespan = cacheDefaultLifespan;
    }

    public boolean isEternal() {
        return timeToIdle == 0 && timeToLive == 0;
    }

    public boolean isExpired() {

        if (isEternal()) {
            return false;
        }

        long expirationTime = getExpirationTime();
        long now = System.currentTimeMillis();

        return now > expirationTime;
    }

    public long getExpirationTime() {
        if (isEternal()) {
            return Long.MAX_VALUE;
        }

        long expirationTime;

        long ttlExpiry = createTime + getTimeToLive() * ONE_SECOND;

        long mostRecentTime = Math.max(createTime, lastAccessTime);

        long ttiExpiry = mostRecentTime + getTimeToIdle() * ONE_SECOND;

        if (getTimeToLive() != 0 && (getTimeToIdle() == 0 || getLastAccessTime() == 0)) {
            expirationTime = ttlExpiry;
        } else if (getTimeToLive() == 0) {
            expirationTime = ttiExpiry;
        } else {
            expirationTime = Math.min(ttiExpiry, ttlExpiry);
        }

        return expirationTime;
    }

    public void refreshLastAccessTime() {
        lastAccessTime = System.currentTimeMillis();
    }
}
