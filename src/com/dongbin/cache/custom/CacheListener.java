package com.dongbin.cache.custom;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CacheListener extends Thread {

    private Cache cache;

    private volatile boolean stop = false;

    private volatile long ONE_SECOND = 1000;

    public boolean isStop() {
        return stop;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    public CacheListener(Cache cache) {
        this.cache = cache;
    }

    @Override
    public void run() {
        long time = cache.getCacheConfiguration().getDiskExpiryThreadIntervalSeconds();
        try {
            while (!stop) {
                Thread.sleep(time * ONE_SECOND);
                threadCheckElement();
            }
        } catch (Exception e) {

        }
    }

    private void threadCheckElement() {
        List<Object> keys = new ArrayList<>();
        Map<Object, Element> map = cache.getAll();
        if (map != null && map.size() > 0) {
            for (Object key : map.keySet()) {
                Element element = map.get(key);
                if (element != null && element.isExpired()) {
                    keys.add(key);
                }
            }
        }
        cache.removeElements(keys.toArray());
    }

    public void destory() {
        ONE_SECOND = 0;
        stop = true;
    }


}
