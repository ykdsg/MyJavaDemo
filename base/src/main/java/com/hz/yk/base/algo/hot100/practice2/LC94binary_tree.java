package com.hz.yk.base.algo.hot100.practice2;

import com.hz.yk.base.algo.camp.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * https://leetcode.cn/problems/binary-tree-inorder-traversal/description/
 * 给定一个二叉树的根节点 root ，返回 它的 中序 遍历 。
 * 递归方法比较简单，轮询的话除了构造栈之外，对象上面还需要另一维度的信息，同时需要注意入栈的顺序
 *
 * @author wuzheng.yk
 * @date 2024/3/12
 */
public class LC94binary_tree {

    public List<Integer> inorderTraversalRecur(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                final TreeNode node = stack.pop();
                result.add(node.val);
                cur = node.right;
            }

        }
        return result;
    }

    public List<Integer> inorderTraversalRecur2(TreeNode root) {
        return dfs(root);
    }

    private List<Integer> dfs(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        result.addAll(dfs(root.left));
        result.add(root.val);
        result.addAll(dfs(root.right));
        return result;
    }
}
