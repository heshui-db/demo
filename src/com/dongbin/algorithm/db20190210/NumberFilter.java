package com.dongbin.algorithm.db20190210;

/**
 * 大量数字 判断是否存在
 * <p>
 * 如果用hashSet 存的是整个值 这里用位来判断
 */
public class NumberFilter {

    private int[] bitSet;

    /**
     * 给你一个这组数中的最大值
     *
     * @param max
     */
    public NumberFilter(int max) {
        this.bitSet = new int[max / 32 + 1];
    }

    public boolean isContains(int a) {
        int index = a / 32;
        int bit = a % 32;

        if (((bitSet[index] >> bit) & 1) == 1) {
            return true;
        } else {
            bitSet[index] = bitSet[index] | (1 << bit);
            return false;
        }
    }
}
