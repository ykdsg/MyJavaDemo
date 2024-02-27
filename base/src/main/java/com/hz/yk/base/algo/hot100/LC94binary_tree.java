package com.hz.yk.base.algo.hot100;

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
 * @date 2024/2/27
 */
public class LC94binary_tree {

    /**
     * 递归方法比较简单，但是性能不一定是最好的
     *
     * @param root
     * @return
     */
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

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Deque<ColorNode> stack = new ArrayDeque<>();
        stack.push(new ColorNode(0, root));

        while (!stack.isEmpty()) {
            final ColorNode cnode = stack.pop();
            TreeNode node = cnode.node;
            if (node == null) {
                continue;
            }
            if (cnode.color == 0) {
                //需要注意这里的顺序，因为是栈所以要先放最后轮询的节点
                stack.push(new ColorNode(0, node.right));
                stack.push(new ColorNode(1, node));
                stack.push(new ColorNode(0, node.left));
            } else {
                result.add(node.val);
            }
        }
        return result;
    }

    static class ColorNode {

        /**
         * 表示是否应该输出，0，表示未遍历子树，1表示输出当前值
         */
        int color;
        TreeNode node;

        public ColorNode(int color, TreeNode node) {
            this.color = color;
            this.node = node;
        }
    }

}
