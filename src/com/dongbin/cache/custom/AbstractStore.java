package com.dongbin.cache.custom;

import java.util.Map;

public abstract class AbstractStore implements Store {

    protected Map<Object, Element> map;

    public AbstractStore(Map<Object, Element> map) {
        this.map = map;
    }

    @Override
    public Element get(Object key) {
        Element element = map.get(key);
        return element;
    }

    public Map<Object, Element> getAll() {
        return map;
    }

    @Override
    public Element put(Element e) {
        return map.put(e.getKey(), e);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public void removeElements(Object ... keys) {
        if (keys != null) {
            for (Object key : keys) {
                map.remove(key);
            }
        }
    }

    @Override
    public Integer getSize() {
        return map.size();
    }
}
