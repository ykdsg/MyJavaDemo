package com.hz.yk.algo.camp.ch09_search;

import com.hz.yk.algo.camp.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * 给定一棵二叉树的根节点 root ，请找出该二叉树中每一层的最大值。
 * @author wuzheng.yk
 * @date 2023/7/27
 */
public class LC515find_largest_value_in_each_tree_row {
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> resultList = new ArrayList<>();
        if (root == null) {
            return resultList;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            final int size = queue.size();
            int layerMax = Integer.MIN_VALUE;
            for (int i = 0; i < size; i++) {
                final TreeNode node = queue.poll();
                layerMax = Math.max(layerMax, node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            resultList.add(layerMax);
        }
        return resultList;   
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(3, 5, 3);
        root.right = new TreeNode(2, 1, 9);
        LC515find_largest_value_in_each_tree_row test = new LC515find_largest_value_in_each_tree_row();
        final List<Integer> result = test.largestValues(root);
        System.out.println(result);
    }
}
