package com.dongbin.cache.custom;

import java.util.Collection;

public interface Store {

    /**
     * 获取缓存名称
     *
     * @return
     */
    String getName();

    //存放元素
    Element put(Element e);

    Collection<Element> putAll(Collection<Element> elements);

    //获取元素
    Element get(Object key);

    //清除元素
    void clear();

    //移除元素
    void removeElements(Object... keys);

    //获取元素长度
    Integer getSize();
}
