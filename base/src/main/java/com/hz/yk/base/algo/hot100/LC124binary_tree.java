package com.hz.yk.base.algo.hot100;

import com.hz.yk.base.algo.camp.TreeNode;

/**
 * https://leetcode.cn/problems/binary-tree-maximum-path-sum/description/
 * 二叉树中的 路径 被定义为一条节点序列，序列中每对相邻节点之间都存在一条边。同一个节点在一条路径序列中 至多出现一次 。
 * 该路径 至少包含一个 节点，且不一定经过根节点。路径和 是路径中各节点值的总和。
 *
 * @author wuzheng.yk
 * @date 2024/3/21
 */
public class LC124binary_tree {

    private int max = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        dfs(root);
        return max;
    }

    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        final int leftInnerMax = dfs(root.left);
        final int rightInnerMax = dfs(root.right);
        // 作为内圈的情况
        final int innerMax = root.val + leftInnerMax + rightInnerMax;
        max = Math.max(max, innerMax);

        //对外提供的情况，因为不能走回头路，所以left 和right 只能选一边
        final int outputMax = root.val + Math.max(leftInnerMax, rightInnerMax);
        return Math.max(outputMax, 0);
    }

}
