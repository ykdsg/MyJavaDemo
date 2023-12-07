package com.hz.yk.base.algo.camp.ch06_tree;

import com.hz.yk.base.algo.camp.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 给定一个二叉树的根节点 root ，返回 它的 中序 遍历
 * @author wuzheng.yk
 * @date 2023/6/30
 */
public class LC94binary_tree_inorder_traversal {

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        result.addAll(inorderTraversal(root.left));
        result.add(root.val);
        result.addAll(inorderTraversal(root.right));

        return result;
    }

    /**
     * 使用轮训方式，相当于自己构建了一个栈，这里巧妙的用多以维度的信息（颜色）表示是否已经解构相应的子树
     * 使用颜色标记节点的状态，新节点为白色（0），已访问的节点为灰色（1）。
     * 如果遇到的节点为白色，则将其标记为灰色，然后将其右子节点、自身、左子节点依次入栈。
     * 如果遇到的节点为灰色，则将节点的值输出。
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Stack<ColorNode> stack = new Stack<>();
        stack.push(new ColorNode(0,root));
        while (!stack.isEmpty()) {
            final ColorNode cnode = stack.pop();
            if (cnode.node == null) {
                continue;
            }
            if (cnode.color == 0) {
                final TreeNode node = cnode.node;
                stack.push(new ColorNode(0, node.right));
                stack.push(new ColorNode(1, node));
                stack.push(new ColorNode(0, node.left));
            } else {
                result.add(cnode.node.val);
            }
        }
        return result;
    }

    static class ColorNode {

        int color;
        TreeNode node;

        public ColorNode(int color, TreeNode node) {
            this.color = color;
            this.node = node;
        }
    }

}

