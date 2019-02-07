package com.dongbin.algorithm.db20190131;

/**
 * 二叉树节点
 */
public class TreeNode {

    int value;

    TreeNode parent;

    TreeNode left;

    TreeNode right;

    public TreeNode(int value, TreeNode parent, TreeNode left, TreeNode right) {
        this.value = value;
        this.parent = parent;
        this.left = left;
        this.right = right;
    }
}
