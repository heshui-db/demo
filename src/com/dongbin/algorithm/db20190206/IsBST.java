package com.dongbin.algorithm.db20190206;

/**
 * 判断是否为搜索二叉树
 */
public class IsBST {

    public static class ReturnData {
        private boolean isBST;
        private int max;
        private int min;

        ReturnData(boolean isBST, int max, int min) {
            this.isBST = isBST;
            this.max = max;
            this.min = min;
        }
    }

    public static boolean isBST(Node root) {
        return process(root).isBST;
    }

    private static ReturnData process(Node node) {
        //base case
        if (node == null) {
            return new ReturnData(true, 0, 0);
        }

        ReturnData leftData = process(node.left);
        ReturnData rightData = process(node.right);

        if (!leftData.isBST || !rightData.isBST || node.value < leftData.max || node.value > rightData.min) {
            return new ReturnData(false, 0, 0);
        }

        int max = Math.max(node.value, Math.max(leftData.max, rightData.max));
        int min = Math.min(node.value, Math.min(leftData.min, rightData.min));

        return new ReturnData(true, max, min);

    }

    //
}
