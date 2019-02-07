package com.dongbin.algorithm;

public class AreConsecutive {

    // 判读数组是否连续
    public boolean areConsecutive(int[] arr) {
        if (arr == null || arr.length < 1) return false;
        if (arr.length == 1) return true;

        // 获取数组中的最大值
        int max = getMax(arr);
        int min = getMin(arr);

        if (max - min + 1 != arr.length) return false;

        boolean[] visited = new boolean[arr.length];
        for (int anArr : arr) {
            if (visited[anArr - min]) {
                return false;
            }

            visited[anArr - min] = true;
        }

        return true;

    }

    private int getMax(int[] arr) {
        int max = arr[0];
        for (int i : arr) {
            if (max < i) max = i;
        }
        return max;
    }

    private int getMin(int[] arr) {
        int min = arr[0];
        for (int i : arr) {
            if (min > i) min = i;
        }
        return min;
    }
}
