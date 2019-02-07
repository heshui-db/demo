package com.dongbin.cache.custom;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class MemoryCache extends AbstractStore implements Store {

    private final CacheConfiguration cacheConfiguration;

    private final static Map<Object, Element> map = new LinkedHashMap<>();

    private final CheckManager checkManager;


    public MemoryCache(CacheConfiguration cacheConfiguration) {
        super(map);
        this.cacheConfiguration = cacheConfiguration;
        this.checkManager = new CheckManager(map, cacheConfiguration);
    }

    @Override
    public String getName() {
        return cacheConfiguration.getCacheName();
    }

    @Override
    public synchronized Collection<Element> putAll(Collection<Element> elements) {
        if (elements == null || elements.size() > 0) {
            throw new RuntimeException("elements cant be null");
        }

        check(elements.size());

        for (Element element : elements) {
            putElementStatus(element);
            put(element);
            super.put(element);
        }
        return elements;
    }

    public void check(int checkSize) {
        if (checkSize <= 0) {
            return;
        }

        Object[] keys = checkManager.checkConfigure(checkSize);

        if (keys != null) {
            removeElements(keys);
        }
    }

    @Override
    public synchronized Element put(Element e) {
        check(1);
        putElementStatus(e);
        return super.put(e);
    }

    public void putElementStatus(Element element) {
        if (!cacheConfiguration.isEternal() && !element.getOpen()) {
            element.setTimeToLive(cacheConfiguration.getTimeToLiveSeconds());
            element.setTimeToIdle(cacheConfiguration.getTimeToIdleSeconds());
        } else {
            element.setTimeToIdle(0);
            element.setTimeToLive(0);
        }
    }

    public void changeElement(Element e) {
        e.addHitCount();
        if (!e.isEternal()) {
            e.refreshLastAccessTime();
        }
    }

    @Override
    public Element get(Object key) {
        Element element = super.get(key);
        if (element != null) {
            if (!element.isExpired()) {
                changeElement(element);
            } else {
                synchronized (this) {
                    removeElements(element.getKey());
                    element = null;
                }
            }
        }
        return element;
    }

    @Override
    public synchronized void removeElements(Object... keys) {
        super.removeElements(keys);
    }


    public boolean checkElement(Element element) {
        if (element == null) {
            throw new NullPointerException("element cant be null");
        }
        if (element.getKey() == null) {
            throw new NullPointerException("element key cant be null");
        }
        return true;
    }

}
