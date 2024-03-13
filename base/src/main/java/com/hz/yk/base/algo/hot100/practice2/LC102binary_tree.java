package com.hz.yk.base.algo.hot100.practice2;

import com.hz.yk.base.algo.camp.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * https://leetcode.cn/problems/binary-tree-level-order-traversal/
 * 给你二叉树的根节点 root ，返回其节点值的 层序遍历 。 （即逐层地，从左到右访问所有节点）。
 *
 * @author wuzheng.yk
 * @date 2024/3/13
 */
public class LC102binary_tree {

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            final int size = queue.size();
            List<Integer> path = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                final TreeNode node = queue.poll();
                path.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            result.add(path);
        }
        return result;
    }

}
