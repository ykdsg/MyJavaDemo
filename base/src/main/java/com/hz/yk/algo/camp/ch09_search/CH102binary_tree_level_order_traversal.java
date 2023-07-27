package com.hz.yk.algo.camp.ch09_search;

import com.hz.yk.algo.camp.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * 给你二叉树的根节点 root ，返回其节点值的 层序遍历 。 （即逐层地，从左到右访问所有节点）。
 *
 * @author wuzheng.yk
 * @date 2023/7/26
 */
public class CH102binary_tree_level_order_traversal {

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            // 这里是关键，需要控制当前层的数量
            final int size = queue.size();
            List<Integer> layerList = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                final TreeNode node = queue.poll();
                layerList.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            result.add(layerList);
        }
        return result;
    }

    //使用dfs的方案
    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null)
            return res;
        dfs(0, root, res);
        return res;
    }

    private void dfs(int index, TreeNode root, List<List<Integer>> res) {
        if (root == null) {
            return;
        }
        //当前层的逻辑
        if (res.size() <= index) {
            res.add(new ArrayList<>());
        }
        res.get(index).add(root.val);
        
        if (root.left != null) {
            dfs(index + 1, root.left, res);
        }
        if (root.right != null) {
            dfs(index + 1, root.right, res);
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20, 15, 7);
        CH102binary_tree_level_order_traversal test = new CH102binary_tree_level_order_traversal();
        final List<List<Integer>> result = test.levelOrder(root);
        System.out.println(result);
    }

}
