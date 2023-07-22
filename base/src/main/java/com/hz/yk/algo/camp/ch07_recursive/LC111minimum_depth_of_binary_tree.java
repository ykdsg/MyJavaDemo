package com.hz.yk.algo.camp.ch07_recursive;

import com.hz.yk.algo.camp.TreeNode;

/**
 * 给定一个二叉树，找出其最小深度。
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 * 说明：叶子节点是指没有子节点的节点。
 *
 * @author wuzheng.yk
 * @date 2023/7/22
 */
public class LC111minimum_depth_of_binary_tree {

    /**
     * 这个不能复用类似最大深度的逻辑，因为这里的最小深度是要到叶子节点
     *
     * @param root
     * @return
     */
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        //1.如果左节点和右节点都为空，直接返回1
        if (root.left == null && root.right == null) {
            return 1;
        }
        //2.当 root 节点左右孩子有一个为空时，返回不为空的孩子节点的深度
        if (root.left == null || root.right == null) {
            //这里其中一个节点为空，说明m1和m2有一个必然为0，所以可以返回m1 + m2 + 1;算是比较灵活的处理方式
            return minDepth(root.left) + minDepth(root.right) + 1;
        }
        //3.如果左右节点都有值，返回较小深度+1
        return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
    }
}
