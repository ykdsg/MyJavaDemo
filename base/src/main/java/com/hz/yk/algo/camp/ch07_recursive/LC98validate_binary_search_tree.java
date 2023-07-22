package com.hz.yk.algo.camp.ch07_recursive;

import com.hz.yk.algo.camp.TreeNode;

/**
 * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
 * 有效 二叉搜索树定义如下：
 * 节点的左子树只包含 小于 当前节点的数。
 * 节点的右子树只包含 大于 当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 *
 * @author wuzheng.yk
 * @date 2023/7/22
 */
public class LC98validate_binary_search_tree {

    //注意这里需要使用Long.MIN_VALUE，因为测试用例会使用Integer.MIN_VALUE
    long pre = Long.MIN_VALUE;

    /**
     * 利用了中序遍历是递增的特点，这个不太容易想到
     * 
     * @param root
     * @return
     */
    public boolean isValidBST2(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (!isValidBST2(root.left)) {
            return false;
        }
        // 访问当前节点：如果当前节点小于等于中序遍历的前一个节点，说明不满足BST，返回 false；否则继续遍历。
        if (root.val <= pre) {
            return false;
        }
        pre = root.val;
        if (!isValidBST2(root.right)) {
            return false;
        }

        return true;
    }

    /**
     * 直球思维，整体未免啰嗦
     *
     * @param root
     * @return
     */
    public boolean isValidBST1(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isValidLeft(root, root.left) && isValidRight(root, root.right) && isValidBST1(root.left) && isValidBST1(
                root.right);
    }

    private boolean isValidLeft(TreeNode root, TreeNode leftNode) {
        if (leftNode == null) {
            return true;
        }
        if (leftNode.val >= root.val) {
            return false;
        }
        return isValidLeft(root, leftNode.left) && isValidLeft(root, leftNode.right);
    }

    private boolean isValidRight(TreeNode root, TreeNode rightNode) {
        if (rightNode == null) {
            return true;
        }
        if (rightNode.val <= root.val) {
            return false;
        }
        return isValidRight(root, rightNode.left) && isValidRight(root, rightNode.right);
    }
}
