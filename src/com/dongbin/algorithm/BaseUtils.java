package com.dongbin.algorithm;

public class BaseUtils {

    public static void printArray(int[] array) {
        if (array == null || array.length == 0) {
            return;
        }

        System.out.println();
        for (int index = 0; index < array.length; index++) {
            System.out.print(array[index] + "  ");
        }
        System.out.println();
    }

    public static void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
}
