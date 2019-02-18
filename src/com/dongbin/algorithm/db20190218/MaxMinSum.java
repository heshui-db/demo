package com.dongbin.algorithm.db20190218;

import java.util.Stack;

/**
 * 一个数组min乘sum 为最小值和
 * <p>
 * <p>
 * 求一个数组所有子数组最大的最小值和
 * <p>
 * 数组值 都大于0
 */
public class MaxMinSum {

    public static void main(String[] args) {
        int[] array = {2, 1, 2, 3, 2, 33, 5, 6};
        System.out.println(getMaxMinSum(array));

//        int[] arraySum = new int[array.length];
//        for (int i = 0; i < array.length; i++) {
//            arraySum[i] = i == 0 ? array[0] : array[i] + arraySum[i - 1];
//        }
//
//        BaseUtils.printArray(arraySum);
//
//        System.out.println(getSumArea(arraySum, 1, 3));


    }

    /**
     *  L-R 包含该
     * @param array
     * @param L
     * @param R
     * @return
     */
    private static int getSumArea(int[] array, int L, int R) {
        if (L == 0) {
            return array[R];
        }
        return array[R] - array[L - 1];
    }


    public static int getMaxMinSum(int[] array) {

        if (array == null || array.length == 0) {
            return 0;
        }

        int[] arraySum = new int[array.length];

        for (int i = 0; i < array.length; i++) {
            arraySum[i] = i == 0 ? array[0] : array[i] + arraySum[i - 1];
        }

        int max = 0;

        /**
         * 单调栈
         */
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < array.length; i++) {

            while (!stack.isEmpty() && array[i] <= array[stack.peek()]) {
                int curIndex = stack.pop();
                int L = stack.isEmpty() ? 0: stack.peek()+1;
                max = Math.max(max, array[curIndex] * getSumArea(arraySum, L, i - 1));

            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            int curIndex = stack.pop();
            int L = stack.isEmpty() ? 0 : stack.peek()+1;
            max = Math.max(max, array[curIndex] * getSumArea(arraySum,L,array.length-1));
        }

        return max;
    }
}
