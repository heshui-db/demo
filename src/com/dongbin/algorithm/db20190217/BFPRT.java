package com.dongbin.algorithm.db20190217;

import static com.dongbin.algorithm.BaseUtils.printArray;
import static com.dongbin.algorithm.BaseUtils.swap;

/**
 * 查找第K小 或者第K大的数
 */
public class BFPRT {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 2, 1, 3};
        int[] res = getminKNumByHeap(arr, 5);
        printArray(res);
    }

    /**
     * 利用堆结构  大顶堆
     *
     * @param arr
     * @param k
     * @return
     */
    public static int[] getminKNumByHeap(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k < 1 || k > arr.length) {
            return arr;
        }

        int[] res = new int[k];

        for (int i = 0; i < arr.length; i++) {
            if (i < k) {
                heapInsert(res, arr[i], i);
            } else {
                if (res[0] > arr[i]) {
                    res[0] = arr[i];
                    heapify(res, 0, k);
                }
            }
        }

        return res;
    }

    private static void heapify(int[] res, int index, int size) {
        int left = 2 * index + 1;
        int right = left + 1;
        int largest = index;
        while (left < size) {
            if (res[left] > res[index]) {
                largest = left;
            }

            if (right < size && res[right] > res[index]) {
                largest = right;
            }

            if (largest != index) {
                swap(res, largest, index);
            } else {
                break;
            }
            index = largest;

            left = 2 * index + 1;

            right = left + 1;
        }
    }

    private static void heapInsert(int[] res, int value, int index) {
        res[index] = value;

        while (index > 0) {
            int parent = (index - 1) / 2;
            if (res[parent] < value) {
                swap(res, parent, index);
                index = parent;
            } else {
                break;
            }
        }
    }

    /**
     * 查找第K的数
     *
     * @param arr
     * @param k
     * @return
     */
    public static int getMinKNumByBFRT(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k < 1 || k > arr.length) {
            return -1;
        }

        return select(arr, 0, arr.length - 1, k - 1);
    }

    private static int select(int[] arr, int L, int R, int k) {
        /**
         * random 求第K小的值原理是这样的
         *
         * 如果要优化 只能优化这个random 让每一次 排除的数据尽可能的多
         *
         * 在快速排序中也有这个问题 情况不好的状况下 时间复杂度为O(n*n)
         */
        int random = (L + R) / 2;
        int[] res = partition(arr, L, R, arr[random]);

        if (res[0] > k) {
            return select(arr, L, res[0] - 1, k);
        } else if (res[1] < k) {
            return select(arr, res[1] + 1, R, k);
        } else {
            return arr[res[0]];
        }
    }

    /**
     * 快速排序中的 partition BFRT也用这个
     *
     * @param arr
     * @param L
     * @param R
     * @param value
     * @return
     */
    private static int[] partition(int[] arr, int L, int R, int value) {
        int begin = L - 1;
        int end = R + 1;
        int cur = L;

        while (cur < end) {
            if (arr[cur] < value) {
                swap(arr, ++begin, cur++);
            } else if (arr[cur] > value) {
                swap(arr, --end, cur);
            } else {
                cur++;
            }
        }

        int[] res = {begin + 1, end - 1};
        return res;
    }
}
