package com.hz.yk.base.algo.camp.ch07_recursive;

import com.hz.yk.base.algo.camp.TreeNode;

/**
 * 给定一个二叉树，找出其最大深度。
 * https://leetcode.cn/problems/maximum-depth-of-binary-tree/
 * @author wuzheng.yk
 * @date 2023/7/22
 */
public class LC104maximum_depth_of_binary_tree {

    /**
     * 使用递归，简洁明了
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
}
