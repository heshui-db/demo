package com.dongbin.algorithm.db20190204;

/**
 * 获取最大值
 */
public class GetGold {

    public static void main(String[] args) {
        int[] arr = {5, 10, 13, 4, 3, 1};
        System.out.println(win(arr));
        System.out.println(dpWin(arr));
    }

    public static int win(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }
        return Math.max(f(array, 0, array.length - 1), s(array, 0, array.length - 1));
    }

    /**
     * 先拿的的人
     *
     * @param array
     * @param L
     * @param R
     * @return
     */
    public static int f(int[] array, int L, int R) {
        if (L == R) {
            return array[L];
        }
        return Math.max(array[L] + s(array, L + 1, R), array[R] + s(array, L, R - 1));
    }

    /**
     * 后拿的人
     *
     * @param array
     * @param L
     * @param R
     * @return
     */
    public static int s(int[] array, int L, int R) {
        if (L == R) {
            return 0;
        }
        return Math.min(f(array, L + 1, R), f(array, L, R - 1));
    }

    /**
     * 动态规划
     *
     * @param array
     * @return
     */
    public static int dpWin(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }

        int[][] f = new int[array.length][array.length];
        int[][] s = new int[array.length][array.length];

        for (int i = 0; i < array.length; i++) {
            f[i][i] = array[i];
        }

        for (int k = 1; k < array.length; k++) {
            for (int row = 0, col = k; col < array.length; col++, row++) {
                s[row][col] = Math.min(f[row + 1][col], f[row][col - 1]);
                f[row][col] = Math.max(array[row] + s[row + 1][col], array[col] + s[row][col - 1]);
            }
        }

        return Math.max(f[0][array.length - 1], s[0][array.length - 1]);
    }
}
