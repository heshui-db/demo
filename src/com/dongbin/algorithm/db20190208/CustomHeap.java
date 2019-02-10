package com.dongbin.algorithm.db20190208;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * 自定义堆 可以自动调整
 */
public class CustomHeap<T> {

    //元素数组
    private Object[] elementDatas;

    //元素所在的index位置
    private Map<T, Integer> elementIndexMap;

    //堆的大小
    private int heapSize;

    private Comparator<? super T> comparator;

    public CustomHeap(int size, Comparator<? super T> comparator) {
        this.elementDatas = new Object[size];
        this.elementIndexMap = new HashMap<>();
        this.heapSize = 0;
        this.comparator = comparator;
    }

    //添加元素
    public void add(T t) {
        //判断这个元素是否添加过
        if (elementIndexMap.containsKey(t)) {
            //向上或向下调整
            heapInsert(elementIndexMap.get(t));
            heapify(elementIndexMap.get(t));
        } else {
            elementDatas[heapSize] = t;
            elementIndexMap.put(t, heapSize);
            heapInsert(heapSize);
            heapSize++;
        }
    }

    //弹出堆顶元素
    public T peek() {
        if (heapSize == 0) {
            return null;
        }
        T t = (T) elementDatas[0];
        heapSize = heapSize - 1;
        swap(0, heapSize);
        elementDatas[heapSize] = null;
        elementIndexMap.remove(t);
        heapify(0);
        return t;
    }

    public boolean isEmpty() {
        return heapSize == 0;
    }

    /**
     * 向上调整
     *
     * @param index
     */
    private void heapInsert(int index) {

        while (siftUpUsingComparator(index, (index - 1) / 2) > 0) {
            swap(index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    private int siftUpUsingComparator(int a, int b) {
        return comparator.compare((T) elementDatas[a], (T) elementDatas[b]);
    }

    /**
     * 向下调整
     *
     * @param index
     */
    private void heapify(int index) {
        int left = 2 * index + 1;

        while (left < heapSize) {
            //取左还是右
            int small = (left + 1) < heapSize && siftUpUsingComparator(left + 1, left) > 0 ? left + 1 : left;
            if (siftUpUsingComparator(index, small) > 0) {
                break;
            }
            swap(index, small);
            index = small;
            left = 2 * index + 1;

        }
    }

    /**
     * a , b 位置上交换
     *
     * @param a
     * @param b
     */
    private void swap(int a, int b) {
        elementIndexMap.put((T) elementDatas[a], b);
        elementIndexMap.put((T) elementDatas[b], a);
        Object data = elementDatas[a];
        elementDatas[a] = elementDatas[b];
        elementDatas[b] = data;
    }

}
