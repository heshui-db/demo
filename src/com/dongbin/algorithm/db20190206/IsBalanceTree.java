package com.dongbin.algorithm.db20190206;

public class IsBalanceTree {

    public static class ReturnData {
        //是否为平衡树
        public boolean isBalanced;
        //高度
        public int height;

        public ReturnData(boolean isBalanced, int height) {
            this.isBalanced = isBalanced;
            this.height = height;
        }
    }

    public static boolean isBalanced(Node root) {
        return process(root).isBalanced;
    }

    /**
     * 只关心是不是平衡二叉树
     *
     * @param node
     * @return
     */
    public static ReturnData process(Node node) {
        //base case
        if (node == null) {
            return new ReturnData(true, 0);
        }

        ReturnData leftData = process(node.left);
        ReturnData rightData = process(node.right);

        int height = Math.max(leftData.height, rightData.height) + 1;

        //左子树和右子树是否为平衡二叉树
        if (!leftData.isBalanced || !rightData.isBalanced) {
            return new ReturnData(false, height);
        }

        //判断左子树和右子树的高度差不能大于1

        if (Math.abs(leftData.height - rightData.height) > 1) {
            return new ReturnData(false, height);
        }

        return new ReturnData(true, height);
    }
}
