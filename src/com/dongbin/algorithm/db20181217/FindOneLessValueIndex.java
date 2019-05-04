package com.dongbin.algorithm.db20181217;

public class FindOneLessValueIndex {

    public static int getLessValueIndex(int[] array) {
        if (array == null || array.length == 0) {
            return -1;
        }

        if (array.length == 1 || array[0] < array[1]) {
            return 0;
        }

        if (array[array.length - 1] < array[array.length - 2]) {
            return array.length - 1;
        }

        int left = 1, right = array.length - 2;

        int mid;

        while (left < right) {
            mid = (left + right) / 2;
            if (array[mid] > array[mid - 1]) {
                right = mid - 1;
            } else if (array[mid] > array[mid + 1]) {
                left = mid + 1;
            } else {
                return mid;
            }
        }

        return left;
    }

    public static void main(String[] args) {
        int[] array = {6,5,2,1,2,6};
        System.out.println(getLessValueIndex(array));

        System.out.println();
    }
}
