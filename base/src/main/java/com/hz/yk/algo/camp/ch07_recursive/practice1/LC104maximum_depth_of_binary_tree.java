package com.hz.yk.algo.camp.ch07_recursive.practice1;

import com.hz.yk.algo.camp.TreeNode;

/**
 * 给定一个二叉树 root ，返回其最大深度。
 *
 * 二叉树的 最大深度 是指从根节点到最远叶子节点的最长路径上的节点数。
 * @author wuzheng.yk
 * @date 2023/8/9
 */
public class LC104maximum_depth_of_binary_tree {

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    public static void main(String[] args) {
        LC104maximum_depth_of_binary_tree test = new LC104maximum_depth_of_binary_tree();
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20, 15, 7);

        final int result = test.maxDepth(root);
        System.out.println(result);
        

    }
}
