package com.dongbin.algorithm.db20181222;

public class FIndMax {

    public static void main(String[] args) {
        int[] array = {1, 2, 122, 123, 21, 2};
        System.out.println(process(array, 0, array.length - 1));
    }


    private static int process(int[] array, int L, int R) {
        if (L == R) {
            return array[L];
        }

        int mid = (L + R) / 2;
        int LMax = process(array, L, mid);
        int RMax = process(array, mid + 1, R);

        return Math.max(LMax, RMax);
    }
}
