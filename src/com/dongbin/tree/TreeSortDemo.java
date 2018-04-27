package com.dongbin.tree;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by dongbin on 2018/4/8.
 */
public class TreeSortDemo {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        List<TreeNode> children;//深度优先，广度优先

        TreeNode(int x) {
            val = x;
        }

        public TreeNode getLeft() {
            return left;
        }

        public void setLeft(TreeNode left) {
            this.left = left;
        }

        public TreeNode getRight() {
            return right;
        }

        public void setRight(TreeNode right) {
            this.right = right;
        }

        public List<TreeNode> getChildren() {
            return children;
        }

        public void setChildren(List<TreeNode> children) {
            this.children = children;
        }
    }

    public static void main(String[] args) {

    }

    //前序遍历 根-左-右
    public List<TreeNode> preOrder(TreeNode treeNode, List<TreeNode> nodeList) {

        nodeList.add(treeNode);

        if (treeNode.getLeft() != null) {
            preOrder(treeNode.getLeft(), nodeList);
        }

        if (treeNode.getRight() != null) {
            preOrder(treeNode.getRight(), nodeList);
        }
        return nodeList;
    }

    //非递归 借助栈实现
    public List<TreeNode> preSort(TreeNode treeNode, List<TreeNode> nodeList) {
        Stack<TreeNode> nodeStack = new Stack<>();
        Iterator iterable = nodeStack.iterator();
        while (iterable.hasNext()){

        }
        nodeStack.clear();

        while (true) {
            while (treeNode != null) {
                nodeList.add(treeNode);
                nodeStack.push(treeNode);
                treeNode = treeNode.getLeft();
            }

            if (nodeStack.isEmpty()) break;
            treeNode = nodeStack.pop();
            treeNode = treeNode.getRight();

        }
        return nodeList;
    }

    public List<TreeNode> preSort1(TreeNode treeNode, List<TreeNode> nodeList) {
        Stack<TreeNode> nodeStack = new Stack<>();
        if (treeNode != null) nodeStack.push(treeNode);
        while (!nodeStack.isEmpty()) {
            TreeNode node = nodeStack.pop();
            nodeList.add(node);
            if (node.getRight() != null) nodeStack.push(node.getRight());
            if (node.getLeft() != null) nodeStack.push(node.getLeft());
        }
        return nodeList;
    }

    //中序遍历 左-根-右
    public List<TreeNode> midOrder(TreeNode treeNode, List<TreeNode> nodeList) {

        if (treeNode.getLeft() != null) {
            midOrder(treeNode.getLeft(), nodeList);
        }

        nodeList.add(treeNode);

        if (treeNode.getRight() != null) {
            midOrder(treeNode.getRight(), nodeList);
        }
        return nodeList;
    }

    //中序遍历 借助栈实现
    public List<TreeNode> midSort(TreeNode treeNode, List<TreeNode> nodeList) {
        Stack<TreeNode> stack = new Stack<>();
        while (true) {
            while (treeNode != null) {
                stack.push(treeNode);
                treeNode = treeNode.getLeft();
            }

            if (stack.isEmpty()) break;
            treeNode = stack.pop();
            nodeList.add(treeNode);
            treeNode = treeNode.getRight();
        }

        return nodeList;
    }


    //后序遍历 左-右-根
    public List<TreeNode> postOrder(TreeNode treeNode, List<TreeNode> nodeList) {

        if (treeNode.getLeft() != null) {
            postOrder(treeNode.getLeft(), nodeList);
        }

        if (treeNode.getRight() != null) {
            postOrder(treeNode.getRight(), nodeList);
        }

        nodeList.add(treeNode);

        return nodeList;
    }

    //非递归后序遍历
    public List<TreeNode> postSort(TreeNode treeNode, List<TreeNode> nodeList) {

        Stack<TreeNode> stack = new Stack<>();
        while (true) {
            if (treeNode != null) {
                stack.push(treeNode);
                treeNode = treeNode.getLeft();
            } else {
                if (stack.isEmpty()) break;
                if (stack.lastElement().getRight() == null) {
                    treeNode = stack.pop();
                    nodeList.add(treeNode);
                    if (stack.lastElement().getRight() == treeNode) {
                        treeNode = stack.pop();
                        nodeList.add(treeNode);
                        if (stack.isEmpty()) break;
                    }
                }

                if (!stack.isEmpty()) {
                    treeNode = stack.lastElement().getRight();
                } else {
                    treeNode = null;
                }
            }
        }

        return nodeList;
    }

    //层次遍历
    public List<TreeNode> layerOrder(TreeNode treeNode, List<TreeNode> nodeList) {
        Queue<TreeNode> queue = new LinkedList<>();
        if (treeNode != null) queue.offer(treeNode);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            nodeList.add(node);
            if (node.getLeft() != null) queue.offer(node.getLeft());
            if (node.getRight() != null) queue.offer(node.getRight());
        }
        return nodeList;
    }

    //深度优先 和 二叉树的前序遍历一样
    public List<TreeNode> depthFirst(TreeNode treeNode, List<TreeNode> nodeList) {
        Stack<TreeNode> stack = new Stack<>();
        if (treeNode != null) stack.push(treeNode);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            nodeList.add(node);
            List<TreeNode> children = node.getChildren();
            if (children != null && children.size() > 0) {
                for (TreeNode t : children) {
                    stack.push(t);
                }
            }
        }
        return nodeList;
    }

    /**
     * 广度优先 和层次排序一样     *
     *
     * @param treeNode
     * @param nodeList
     * @return
     */

    public List<TreeNode> breadthFirst(TreeNode treeNode, List<TreeNode> nodeList) {
        Queue<TreeNode> queue = new LinkedList<>();
        if (treeNode != null) queue.offer(treeNode);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            nodeList.add(node);
            List<TreeNode> children = node.getChildren();
            if (children != null && children.size() > 0) {
                for (TreeNode t : children) {
                    queue.offer(t);
                }
            }
        }

        return nodeList;
    }

}

