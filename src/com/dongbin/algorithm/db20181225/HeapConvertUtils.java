package com.dongbin.algorithm.db20181225;

import com.dongbin.algorithm.BaseUtils;

public class HeapConvertUtils {

    public static void main(String[] args) {
        int[] array = {2, 3, 42, 34, 53, 1, 6};
        convertMaxHeap(array);
        BaseUtils.printArray(array);
    }

    private static void convertMaxHeap(int[] array) {

        if (array == null || array.length <= 1) {
            return;
        }

        for (int i = 0; i < array.length; i++) {
            heapInsert(array, i);
        }
    }

    /**
     * 向上调整
     *
     * @param array
     * @param i
     */
    private static void heapInsert(int[] array, int i) {

        while (array[i] > array[(i - 1) / 2]) {
            swap(array, i, (i - 1) / 2);
            i = (i - 1) / 2;
        }
    }

    private static void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    //调整大根堆 如果i位置值发生变化 向下调整
    private static void heapify(int array[], int i, int heapSize) {
        int L = 2 * i + 1;//左孩子

        while (L < heapSize) {
            int largest = L + 1 < heapSize && array[L] < array[L + 1] ? L + 1 : L;
            if (array[i] > array[largest]) {
                break;
            }
            swap(array, i, largest);
            i = largest;
            L = 2 * i + 1;
        }
    }

}
