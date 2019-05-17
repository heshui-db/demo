package com.dongbin.db20190517;


import java.util.HashMap;
import java.util.Map;

/**
 * copy on write
 * <p>
 * 写的时候 copy一个新的对象，新的对象写完成后，将原对象的引用指向新的对象
 * <p>
 * 主要用在读多写少的地方，而且不强调数据的强一致性
 *
 * @param <K>
 * @param <V>
 */

public class CopyOnWriteMap<K, V> {

    private volatile Map<K, V> internalMap;

    public CopyOnWriteMap(Map<K, V> internalMap) {
        this.internalMap = internalMap;
    }

    public V put(K k, V v) {
        synchronized (this) {
            Map<K, V> newMap = new HashMap<>(internalMap);
            V val = newMap.put(k, v);
            internalMap = newMap;
            return val;
        }
    }

    public V get(K k) {
        return internalMap.get(k);
    }

    public void putAll(Map<? extends K, ? extends V> data) {
        synchronized (this) {
            Map<K, V> newMap = new HashMap<>(internalMap);
            newMap.putAll(data);
            internalMap = newMap;
        }
    }
}
