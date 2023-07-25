package com.hz.yk.algo.camp;

/**
 * @author wuzheng.yk
 * @date 2023/6/30
 */
public class TreeNode {

    public int val;
    public TreeNode left;
    public TreeNode right;

    TreeNode() {}

    public TreeNode(int val) {this.val = val;}

    public TreeNode(int val, int left, int right) {
        this(val, new TreeNode(left), new TreeNode(right));
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
