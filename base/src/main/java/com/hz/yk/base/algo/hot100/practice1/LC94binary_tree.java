package com.hz.yk.base.algo.hot100.practice1;

import com.hz.yk.base.algo.camp.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode.cn/problems/binary-tree-inorder-traversal/description/
 * 给定一个二叉树的根节点 root ，返回 它的 中序 遍历 。
 * 递归方法比较简单，轮询的话除了构造栈之外，对象上面还需要另一维度的信息，同时需要注意入栈的顺序
 *
 * @author wuzheng.yk
 * @date 2024/2/28
 */
public class LC94binary_tree {

    /**
     * 最快的解法也最简洁，但是不容易理解
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversalFast(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        TreeNode curr = root;
        Stack<TreeNode> stack = new Stack<>();
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            result.add(curr.val);
            curr = curr.right;
        }
        return result;
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        Deque<ColorNode> stack = new ArrayDeque<>();
        stack.push(new ColorNode(root, 0));
        List<Integer> result = new ArrayList<>();
        while (!stack.isEmpty()) {
            final ColorNode cnode = stack.pop();
            final TreeNode node = cnode.node;
            if (node == null) {
                continue;
            }
            if (cnode.color == 0) {
                stack.push(new ColorNode(node.right, 0));
                stack.push(new ColorNode(node, 1));
                stack.push(new ColorNode(node.left, 0));
            } else {
                result.add(node.val);
            }
        }
        return result;
    }

    static class ColorNode {

        TreeNode node;
        int color;

        public ColorNode(TreeNode node, int color) {
            this.node = node;
            this.color = color;
        }
    }

    public List<Integer> inorderTraversalRecur(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        result.addAll(inorderTraversalRecur(root.left));
        result.add(root.val);
        result.addAll(inorderTraversalRecur(root.right));
        return result;
    }

}
