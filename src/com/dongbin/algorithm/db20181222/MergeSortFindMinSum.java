package com.dongbin.algorithm.db20181222;


/**
 * 利用归并排序的merge 求最小和
 * <p>
 * 左边的数比右边小 相加之和 为最小和
 */
public class MergeSortFindMinSum {

    public static void main(String[] args) {
        int[] array = {1, 3, 2, 4};
        System.out.println(process(array, 0, array.length - 1));
    }

    private static int process(int[] array, int L, int R) {
        if (L == R) {
            return 0;
        }

        int mid = (L + R) / 2;

        return process(array, L, mid) + process(array, mid + 1, R)
                + merge(array, L, mid, R);
    }

    private static int merge(int[] array, int l, int m, int r) {
        int[] help = new int[r - l + 1];

        int i = 0, p1 = l, p2 = m + 1, res = 0;

        while (p1 <= m && p2 <= r) {
            res += array[p1] < array[p2] ? array[p1] * (r - p2 + 1) : 0;
            help[i++] = array[p1] < array[p2] ? array[p1++] : array[p2++];
        }

        while (p1 <= m) {
            help[i++] = array[p1++];
        }

        while (p2 <= r) {
            help[i++] = array[p2++];
        }

        //copy

        for (i = 0; i < help.length; i++) {
            array[l + i] = help[i];
        }

        return res;
    }
}
