package com.hz.yk.base.algo.camp.ch07_recursive.practice1;

import com.hz.yk.base.algo.camp.TreeNode;

/**
 * 给你一棵二叉树的根节点 root ，翻转这棵二叉树，并返回其根节点
 * https://leetcode.cn/problems/invert-binary-tree/description/
 * @author wuzheng.yk
 * @date 2023/8/9
 */
public class LC226invert_binary_tree {

    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        final TreeNode origLeft = root.left;
        final TreeNode origRight = root.right;
        root.left = invertTree(origRight);
        root.right = invertTree(origLeft);
        return root;
    }


}
