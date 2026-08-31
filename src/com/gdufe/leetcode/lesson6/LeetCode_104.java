package com.gdufe.leetcode.lesson6;

public class LeetCode_104 {
    // LeetCode 104 二叉树的最大深度
    public int maxDepth(TreeNode root) {
        return root == null ? 0 : Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    public static void main(String[] args) {
        LeetCode_104 leetCode_104 = new LeetCode_104();
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        int depth = leetCode_104.maxDepth(root);
        System.out.println(depth);
    }
}
