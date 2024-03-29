package com.hz.yk.base.algo.hot100;

import com.hz.yk.base.algo.camp.TreeNode;

/**
 * https://leetcode.cn/problems/validate-binary-search-tree/description/
 * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
 * 有效 二叉搜索树定义如下：
 * 节点的左子树只包含 小于 当前节点的数。
 * 节点的右子树只包含 大于 当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 *
 * @author wuzheng.yk
 * @date 2024/3/2
 */
public class LC98validate_binary {

    Integer preVal;

    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (!isValidBST(root.left)) {
            return false;
        }
        if (preVal != null && root.val <= preVal) {
            return false;
        }
        preVal = root.val;
        return isValidBST(root.right);
    }

    public static void main(String[] args) {
        LC98validate_binary test = new LC98validate_binary();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(1);
        final boolean result = test.isValidBST(root);
        System.out.println(result);

    }
}
