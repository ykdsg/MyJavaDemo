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

    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (root.left != null && root.left.val >= root.val) {
            return false;
        }
        if (root.right != null && root.right.val <= root.val) {
            return false;
        }
        return isValidBST(root.left) && isValidBST(root.right);

    }
}
