package com.hz.yk.base.algo.hot100.practice3;

import com.hz.yk.base.algo.camp.TreeNode;

/**
 * https://leetcode.cn/problems/maximum-depth-of-binary-tree/description/
 * 给定一个二叉树 root ，返回其最大深度。
 * 二叉树的 最大深度 是指从根节点到最远叶子节点的最长路径上的节点数。
 *
 * @author wuzheng.yk
 * @date 2024/4/26
 */
public class LC104maximum_depth {

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

}
