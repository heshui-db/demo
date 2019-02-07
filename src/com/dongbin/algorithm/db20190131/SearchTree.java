package com.dongbin.algorithm.db20190131;

/**
 * 二叉搜索树
 */
public class SearchTree {

    /**
     * 获取二叉搜索树最小值节点
     *
     * @param root
     * @return
     */
    public TreeNode getMin(TreeNode root) {

        if (root != null) {
            while (root.left != null) {
                root = root.left;
            }
        }
        return root;
    }

    /**
     * 获取二叉搜索树最大值节点
     *
     * @param root
     * @return
     */
    public TreeNode getMax(TreeNode root) {
        if (root != null) {
            while (root.right != null) {
                root = root.right;
            }
        }
        return root;
    }


}
