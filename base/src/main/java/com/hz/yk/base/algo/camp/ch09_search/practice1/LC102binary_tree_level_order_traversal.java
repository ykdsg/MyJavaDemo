package com.hz.yk.base.algo.camp.ch09_search.practice1;

import com.hz.yk.base.algo.camp.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * 给你二叉树的根节点 root ，返回其节点值的 层序遍历 。 （即逐层地，从左到右访问所有节点）。
 *
 * https://leetcode.cn/problems/binary-tree-level-order-traversal/#/description
 * @author wuzheng.yk
 * @date 2023/8/9
 */
public class LC102binary_tree_level_order_traversal {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);


        while (!queue.isEmpty()) {
            final int size = queue.size();
            List<Integer> path = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                final TreeNode node = queue.poll();
                path.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            result.add(path);
        }
        return result;
    }
}
