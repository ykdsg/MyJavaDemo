package com.hz.yk.base.algo.hot100.practice3;

import com.hz.yk.base.algo.camp.TreeNode;

/**
 * https://leetcode.cn/problems/binary-tree-maximum-path-sum/description/
 * 二叉树中的 路径 被定义为一条节点序列，序列中每对相邻节点之间都存在一条边。同一个节点在一条路径序列中 至多出现一次 。
 * 该路径 至少包含一个 节点，且不一定经过根节点。路径和 是路径中各节点值的总和。
 *
 * @author wuzheng.yk
 * @date 2024/4/21
 */
public class LC124binary_tree {

    int result = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        dfs(root);
        return result;

    }

    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        final int left = dfs(root.left);
        final int right = dfs(root.right);
        // 内圈的大小
        result = Math.max(result, root.val + left + right);
        int outer = root.val + Math.max(left, right);
        // 外圈如果小于0的处理
        return Math.max(0, outer);
    }

}
