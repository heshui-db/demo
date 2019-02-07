package com.dongbin.algorithm.db20190101;

import com.dongbin.algorithm.BaseUtils;

public class QuickSort {

    public static void main(String[] args) {
        int[] array = {1, 22, 12, 32, 2, 4};
        quickSort(array);
        BaseUtils.printArray(array);
    }

    private static void quickSort(int[] array) {
        if (array == null || array.length == 0) {
            return;
        }
        process(array, 0, array.length - 1);
    }

    private static void process(int[] array, int L, int R) {
        if (L >= R) {
            return;
        }

        //计算partition
        int[] p = partition(array, L, R);

        process(array, L, p[0] - 1);

        process(array, p[1] + 1, R);
    }

    private static int[] partition(int[] array, int l, int r) {
        int less = l - 1, more = r, index = l;
        while (index < more) {
            if (array[index] < array[r]) {
                BaseUtils.swap(array, ++less, index++);
            } else if (array[index] > array[r]) {
                BaseUtils.swap(array, --more, index);
            } else {
                index++;
            }
        }

        BaseUtils.swap(array, more, r);

        return new int[]{less + 1, more};
    }
}
