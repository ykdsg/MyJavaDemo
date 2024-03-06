package com.hz.yk.base.algo.hot100.practice1;

import com.hz.yk.base.algo.camp.TreeNode;

/**
 * https://leetcode.cn/problems/symmetric-tree/description/
 * 给你一个二叉树的根节点 root ， 检查它是否轴对称。
 *
 * @author wuzheng.yk
 * @date 2024/3/5
 */
public class LC101symmetric_tree {

    public boolean isSymmetric(TreeNode root) {
        return dfs(root.left, root.right);
    }

    private boolean dfs(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }
        if (left.val != right.val) {
            return false;
        }
        return dfs(left.left, right.right) && dfs(left.right, right.left);
    }

}
