package com.hz.yk.algo.camp.ch07_recursive;

import com.hz.yk.algo.camp.TreeNode;

/**
 * @author wuzheng.yk
 * @date 2023/7/21
 */
public class LC226invert_binary_tree {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return root;
        }
        if (root.left == null && root.right == null) {
            return root;
        }

        //表示原来的左右子树
        final TreeNode oLeft = root.left;
        final TreeNode oRight = root.right;
        //交换左右子树的反转
        root.left = invertTree(oRight);
        root.right = invertTree(oLeft);
        return root;
    }
}
