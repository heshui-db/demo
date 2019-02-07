package com.dongbin.cache.custom;

import java.util.Iterator;
import java.util.Map;

public class CheckManager {

    protected Map<Object, Element> map;

    protected CacheConfiguration cacheConfiguration;

    public CheckManager(Map<Object, Element> map, CacheConfiguration cacheConfiguration) {
        this.map = map;
        this.cacheConfiguration = cacheConfiguration;
    }

    public Object[] checkConfigure(int elementSize) {
        int removeSize = map.size() + elementSize - cacheConfiguration.getMaxElementsInMemory();

        //缓存已满
        if (removeSize > 0) {
            if (!cacheConfiguration.getDiskPersistent()) {
                return removeElementByEvictionType(removeSize);
            }
        }

        return null;
    }

    private Object[] removeElementByEvictionType(int removeSize) {
        if (EvictionType.LRU.name().equals(cacheConfiguration.getMemoryStoreEvictionPolicy())) {
            return removeElementByLRU(removeSize);
        }

        return null;
    }

    private Object[] removeElementByLRU(int removeSize) {
        Object[] keys = new Object[removeSize];
        long hits[] = new long[removeSize];

        Iterator<?> iterator = map.keySet().iterator();

        int index = 0;
        while (iterator.hasNext()) {
            Object key = iterator.next();
            Element element = map.get(key);

            if (index < removeSize) {
                keys[index] = element.getKey();
                keys[index] = element.getHitCount();
            } else {
                long minIndex = getMinIndex(hits, element.getHitCount());
                if (minIndex > 0) {
                    keys[(int) minIndex] = element.getKey();
                }
            }
        }

        return keys;
    }

    private long getMinIndex(long[] hits, long hit) {
        for (int index = 0; index < hits.length; index++) {
            if (hits[index] < hit) {
                hits[index] = hit;
                return index;
            }
        }
        return -1;
    }
}
