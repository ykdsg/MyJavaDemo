package com.hz.yk.base.algo.hot100;

import com.hz.yk.base.algo.camp.TreeNode;

/**
 * https://leetcode.cn/problems/flatten-binary-tree-to-linked-list/description/
 * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
 * 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
 * 展开后的单链表应该与二叉树 先序遍历 顺序相同。
 *
 * @author wuzheng.yk
 * @date 2024/3/9
 */
public class LC114flatten_binary {

    TreeNode pre = null;

    /**
     * 相当于反过来遍历，6 5 4 3 2 1，每遍历一个节点就将当前节点的右指针更新为上一个节点。
     * 5指向6，4指向5...
     *
     * @param root
     */
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        flatten(root.right);
        flatten(root.left);
        root.right = pre;
        root.left = null;
        pre = root;

    }

    /**
     * 这个是比较顺的思路
     *
     * @param root
     */
    public void flatten2(TreeNode root) {
        while (root != null) {
            if (root.left == null) {
                root = root.right;
            } else {
                //    找左子树的最右点
                TreeNode pre = root.left;
                while (pre.right != null) {
                    pre = pre.right;
                }
                pre.right = root.right;
                root.right = root.left;
                root.left = null;
                //迭代下一个节点
                root = root.right;
            }
        }
    }
}
