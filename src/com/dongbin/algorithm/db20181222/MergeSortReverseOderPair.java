package com.dongbin.algorithm.db20181222;

public class MergeSortReverseOderPair {

    public static void main(String[] args) {
        int[] array = {};
        process(array, 0, array.length - 1);
    }

    private static void process(int[] array, int l, int r) {
        if (l == r) {
            return;
        }

        int m = (l + r) / 2;

        process(array, l, m);
        process(array, m + 1, r);
        merge(array, l, m, r);
    }

    private static void merge(int[] array, int l, int m, int r) {

        int[] help = new int[r - l + 1];

        int i = 0, p1 = l, p2 = m + 1;

        while (p1 <= m && p2 <= r) {
            if (array[p1] > array[p2]) {
                printReverse(array[p1], array, p2);
            }
            help[i++] = array[p1] >= array[p2] ? array[p1++] : array[p2++];
        }

        while (p1 <= m) {
            help[i++] = array[p1++];
        }

        while (p2 <= r) {
            help[i++] = array[p2++];
        }

        for (i = 0; i < help.length; i++) {
            array[l + i] = help[i];
        }
    }

    private static void printReverse(int value, int[] array, int index) {
        for (int i = index; i < array.length; i++) {
            System.out.println(value + ":" + array[i]);
        }
    }
}
