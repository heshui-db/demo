package com.dongbin.sync;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by dongbin on 2018/1/24.
 */
public class Teanant {
    public final static Map<String, Integer> tenantMap = new ConcurrentHashMap<>();
    public final static Map<String, ReentrantLock> lockMap = new ConcurrentHashMap<>();

    public synchronized static void putTeant(String tenantId) {
        lockMap.putIfAbsent(tenantId, new ReentrantLock());
        Integer count = tenantMap.get(tenantId);
        if (count == null) {
            tenantMap.put(tenantId, 1);
        } else {
            tenantMap.put(tenantId, count + 1);
        }
    }

    public synchronized static boolean getTenantTask(String tenantId) {
        Integer count = tenantMap.get(tenantId);
        if (count == null || count == 0) {
            return false;
        } else {
            tenantMap.put(tenantId, 0);
            return true;
        }
    }

}
