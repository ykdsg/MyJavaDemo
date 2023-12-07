package com.hz.yk.base.algo.camp.ch09_search.practice1;

import com.hz.yk.base.algo.camp.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * 给定一棵二叉树的根节点 root ，请找出该二叉树中每一层的最大值。
 * https://leetcode.cn/problems/find-largest-value-in-each-tree-row/#/description
 * @author wuzheng.yk
 * @date 2023/8/9
 */
public class LC515find_largest_value_in_each_tree_row {

    public List<Integer> largestValues(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            final int size = queue.size();
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < size; i++) {
                final TreeNode node = queue.poll();
                max = Math.max(max, node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            result.add(max);
        }

        return result;

    }

}
