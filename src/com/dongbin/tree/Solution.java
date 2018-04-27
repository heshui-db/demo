package com.dongbin.tree;

/**
 * Created by dongbin on 2018/4/9.
 */
public class Solution {

    //知道前序和中序 重构二叉树
    public TreeNode reConstructBinaryTree(int [] pre,int [] in) {
        if(pre.length==0|| in.length==0 || pre.length!=in.length) return null;
        TreeNode root = new TreeNode(pre[0]);
        if(pre.length==1)return root;
        //查找中序中根节点的位置
        int index=0;
        for(int i=0;i<in.length;i++){
            if(pre[0]==in[i]){
                index = i;
                break;
            }
        }
        //构建左子树
        if(index>0){
            int [] newPre = new int[index];
            int [] newIn = new int[index];
            for(int j=0;j<index;j++){
                newPre[j] = pre[j+1];
                newIn[j] = in[j];
            }
            root.left = reConstructBinaryTree(newPre,newIn);
        }
        //构建右子树
        if(in.length-index-1>0){
            int [] newPre = new int[in.length-index-1];
            int [] newIn = new int[in.length-index-1];
            for(int j=index+1;j<in.length;j++){
                newPre[j-index-1] = pre[j];
                newIn[j-index-1] = in[j];
            }
            root.right = reConstructBinaryTree(newPre,newIn);
        }

        return root;
    }

    public static int numberOfOne2(int n) {
        int count = 0;
        while (n != 0) {
            count++;
            n = n & (n - 1);
        }
        return count;
    }

    public static void main(String[] args) {

        System.out.println(Math.pow(2,2));
    }

}
