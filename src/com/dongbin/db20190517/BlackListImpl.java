package com.dongbin.db20190517;

import java.util.HashMap;
import java.util.Map;

/**
 * 黑名单设置
 */
public class BlackListImpl {

    private CopyOnWriteMap<String, Boolean> map = new CopyOnWriteMap<>(new HashMap<>(1000));

    public boolean isBlackList(String id) {
        return map.get(id);
    }

    public void addBlackList(String id) {
        map.put(id, Boolean.TRUE);
    }

    public void addBlackList(Map<String, Boolean> list) {
        map.putAll(list);
    }

}
