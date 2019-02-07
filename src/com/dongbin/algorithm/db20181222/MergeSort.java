package com.dongbin.algorithm.db20181222;

import com.dongbin.algorithm.BaseUtils;

/**
 * 归并排序
 */
public class MergeSort {

    public static void main(String[] args) {
        int[] array = {2, 1, 23, 21, 2, 3};
        process(array, 0, array.length - 1);
        BaseUtils.printArray(array);
    }

    private static void process(int array[], int L, int R) {
        if (L == R) {
            return;
        }

        int mid = (L + R) / 2;

        process(array, L, mid);
        process(array, mid + 1, R);
        marge(array, L, mid, R);
    }

    private static void marge(int[] array, int L, int mid, int R) {
        int[] help = new int[R - L + 1];
        int i = 0;
        int p1 = L, p2 = mid + 1;

        while (p1 <= mid && p2 <= R) {
            help[i++] = array[p1] > array[p2] ? array[p2++] : array[p1++];
        }

        while (p1 <= mid) {
            help[i++] = array[p1++];
        }

        while (p2 <= R) {
            help[i++] = array[p2++];
        }

        //拷贝
        for (i = 0; i < help.length; i++) {
            array[L + i] = help[i];
        }

    }

}
