package com.dongbin.algorithm.db20190211;

/**
 * 二叉搜索树
 */
public class SearchTree<T extends Comparable<? super T>> {

    private TreeNode<T> root;


    /**
     * 添加节点
     *
     * @return
     */
    public void insert(T key) {
        insert(root, key);
    }

    private void insert(TreeNode<T> treeNode, T key) {

        TreeNode<T> newNode = new TreeNode<>(key);

        if (treeNode == null) {
            root = newNode;
        }

        TreeNode<T> y = treeNode;
        /**
         * 查找放入位置
         */

        while (treeNode != null) {
            y = treeNode;

            int res = newNode.value.compareTo(treeNode.value);
            if (res < 0) {
                treeNode = treeNode.left;
            } else {
                treeNode = treeNode.right;
            }
        }

        /**
         * 放入 newNode
         */
        newNode.parent = y;

        int res = newNode.value.compareTo(y.value);
        if (res < 0) {
            y.left = newNode;
        } else {
            y.right = newNode;
        }

    }


    /**
     * 删除节点
     *
     * @return
     */
    public void remove(T key) {
        remove(root, key);
    }

    /**
     * @param treeNode
     * @param key
     */
    private void remove(TreeNode<T> treeNode, T key) {

        /**
         * 先找到删除的节点
         */
        TreeNode<T> deleteNode = search(key);



    }

    /**
     * 获取 treeNode的前驱
     * 小于该节点的最大节点
     *
     * @param treeNode
     * @return
     */
    public TreeNode<T> predecessor(TreeNode<T> treeNode) {
        /**
         * 如果左子树存在 则获取最大值
         */
        if (treeNode.left != null) {
            return maxium(treeNode);
        }

        /**
         * treeNode没有左子树，则有两种情况
         *
         * 1、treeNode为左孩子，则从当前结点开始向上遍历，直到遍历到的结点为其父结点的右孩子或父结点为NIL，则返回父结点
         * 2、treeNode为右孩子，treeNode的前驱为其父节点
         *
         */

        TreeNode<T> p = treeNode.parent;

        while (p != null && p.left == treeNode) {
            treeNode = p;
            p = p.parent;
        }

        return p;
    }

    /**
     * 获取 treeNode的后驱
     * 大于该节点的最小节点
     *
     * @param treeNode
     * @return
     */
    public TreeNode<T> successor(TreeNode<T> treeNode) {
        /**
         * 如果右子树存在  则获取最小值
         */
        if (treeNode.right != null) {
            return minium(treeNode);
        }

        /**
         * treeNode没有右子树，则有两种情况
         *
         * 1、treeNode为左孩子，treeNode的父节点为其后驱
         * 2、treeNode为右孩子，treeNode
         */

        TreeNode<T> p = treeNode.parent;

        while (p != null && p.right == treeNode) {
            treeNode = p;
            p = p.parent;
        }

        return p;

    }

    /**
     * 查找节点
     *
     * @return
     */
    private TreeNode<T> search(T key) {
        return search(root, key);
    }

    private TreeNode<T> search(TreeNode<T> treeNode, T key) {
        if (treeNode == null || key == null) {
            return null;
        }

        int res = key.compareTo(treeNode.value);
        if (res > 0) {
            return search(treeNode.getRight(), key);
        } else if (res < 0) {
            return search(treeNode.getLeft(), key);
        } else {
            return treeNode;
        }
    }

    /**
     * 非递归版本的查找
     *
     * @param treeNode
     * @param key
     * @return
     */
    private TreeNode<T> search1(TreeNode<T> treeNode, T key) {
        while (treeNode != null) {
            int res = key.compareTo(treeNode.value);
            if (res > 0) {
                treeNode = treeNode.getRight();
            } else if (res < 0) {
                treeNode = treeNode.getLeft();
            } else {
                return treeNode;
            }
        }

        return treeNode;
    }

    /**
     * 获取最大值节点
     *
     * @return
     */
    public TreeNode<T> maxium() {
        return maxium(root);
    }

    private TreeNode<T> maxium(TreeNode<T> treeNode) {
        if (treeNode == null) {
            return null;
        }

        while (treeNode.right == null) {
            treeNode = treeNode.getRight();
        }
        return treeNode;
    }

    /**
     * 获取最小值节点
     *
     * @return
     */
    public TreeNode<T> minium() {
        return minium(root);
    }

    private TreeNode<T> minium(TreeNode<T> treeNode) {
        if (treeNode == null) {
            return null;
        }

        while (treeNode.left != null) {
            treeNode = treeNode.getRight();
        }
        return treeNode;
    }

    private static class TreeNode<T> {
        /**
         * 节点值
         */
        private T value;

        /**
         * 左节点
         */
        private TreeNode<T> left;

        /**
         * 右节点
         */
        private TreeNode<T> right;

        /**
         * 父节点
         */
        private TreeNode<T> parent;


        public TreeNode(T value) {
            this.value = value;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public TreeNode<T> getLeft() {
            return left;
        }

        public void setLeft(TreeNode<T> left) {
            this.left = left;
        }

        public TreeNode<T> getRight() {
            return right;
        }

        public void setRight(TreeNode<T> right) {
            this.right = right;
        }

        public TreeNode<T> getParent() {
            return parent;
        }

        public void setParent(TreeNode<T> parent) {
            this.parent = parent;
        }
    }
}
