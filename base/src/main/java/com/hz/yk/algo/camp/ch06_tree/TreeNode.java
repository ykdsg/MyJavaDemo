package com.hz.yk.algo.camp.ch06_tree;

/**
 * @author wuzheng.yk
 * @date 2023/6/30
 */
class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {}

    TreeNode(int val) {this.val = val;}

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
