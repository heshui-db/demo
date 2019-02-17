package com.dongbin.algorithm.db20190217;

import java.util.LinkedList;

/**
 * 滚动窗口结构
 */
public class MaxWindown {

    private int[] arr;

    private int L;

    private int R;

    private LinkedList<Integer> linkedList;

    public MaxWindown(int[] arr) {
        this.arr = arr;
        this.L = -1;
        this.R = -1;
        linkedList = new LinkedList<>();
    }

    public void addElement() {

        if (R > arr.length - 1) {
            return;
        }

        while (!linkedList.isEmpty() && arr[linkedList.peekLast()] < arr[R + 1]) {
            linkedList.removeLast();
        }
        R++;
        linkedList.addLast(R);

    }

    public void deleteElemnet() {
        if (L == R) {
            return;
        }

        if (!linkedList.isEmpty()) {
            L++;
            while (linkedList.peekFirst() < L) {
                linkedList.removeFirst();
            }
        }
    }

    public Integer getMax() {
        if (linkedList.isEmpty()) {
            return null;
        }
        return linkedList.peekFirst();
    }
}
