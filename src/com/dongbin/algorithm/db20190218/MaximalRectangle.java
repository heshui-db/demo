package com.dongbin.algorithm.db20190218;

import java.util.Stack;

public class MaximalRectangle {

    public static void main(String[] args) {
        int[][] map = {{1, 0, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 0}};

        System.out.println(maxRecSize(map));
    }


    public static int maxRecSize(int[][] map) {
        if (map == null || map.length == 0 || map[0].length == 0) {
            return 0;
        }

        int max = 0;

        int[] height = new int[map[0].length];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                height[j] = map[i][j] == 0 ? 0 : height[j] + 1;
            }
            max = Math.max(max, getMaxFromBottom(height));
        }

        return max;
    }

    /**
     * 类似于获取条形图最大矩阵的面积
     *
     * @param height
     * @return
     */
    public static int getMaxFromBottom(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }

        /**
         * 单调栈
         */
        Stack<Integer> stack = new Stack<>();

        int max = 0;

        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[i] <= height[stack.peek()]) {
                //当前栈弹出元素
                int curIndex = stack.pop();
                //比curIndex小的左边界
                int L = stack.isEmpty() ? -1 : stack.peek();
                max = Math.max(max, (i - L - 1) * height[curIndex]);
            }
            stack.push(i);
        }

        while (stack.isEmpty()) {
            int curIndex = stack.pop();
            int L = stack.isEmpty() ? -1 : stack.peek();
            max = Math.max(max, (height.length - L - 1) * height[curIndex]);
        }

        return max;
    }
}
