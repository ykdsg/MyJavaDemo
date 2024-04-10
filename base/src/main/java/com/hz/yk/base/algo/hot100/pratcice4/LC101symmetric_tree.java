package com.hz.yk.base.algo.hot100.pratcice4;

import com.hz.yk.base.algo.camp.TreeNode;

/**
 * https://leetcode.cn/problems/symmetric-tree/description/
 * 给你一个二叉树的根节点 root ， 检查它是否轴对称。
 *
 * @author wuzheng.yk
 * @date 2024/4/9
 */
public class LC101symmetric_tree {

    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return dfs(root.left, root.right);

    }

    //优化了判断逻辑，整体更简洁清晰
    private boolean dfs(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left != null && right != null && left.val == right.val) {
            return dfs(left.left, right.right) && dfs(left.right, right.left);
        }
        return false;
    }

}
