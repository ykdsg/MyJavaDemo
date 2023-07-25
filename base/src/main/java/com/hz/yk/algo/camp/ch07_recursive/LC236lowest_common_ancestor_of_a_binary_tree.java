package com.hz.yk.algo.camp.ch07_recursive;

import com.hz.yk.algo.camp.TreeNode;

/**
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 * @author wuzheng.yk
 * @date 2023/7/24
 */
public class LC236lowest_common_ancestor_of_a_binary_tree {

    //注意这里是二叉树不是二叉查找树，不能直接利用左子树比root 小，右子树比root 大的特性
    //存在几种情况
    //1.p或q == root
    //2.p、q 在root 异侧
    //3.p或q 在root 同一侧
    //通过前序遍历的方式，在遇到跟p、q 匹配的节点时返回
    //对应上面的情况：
    //1. 可以直接判断
    //2. left、 right都有值，说明分布在 root 异侧
    //3. left、right 只有一个有值，说明分布在root 同侧，因为是前序遍历，因此有值的那个就是公共祖先
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root.val == p.val || root.val == q.val) {
            return root;
        }
        final TreeNode left = lowestCommonAncestor(root.left, p, q);
        final TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }
        return root;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3, 5, 1);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2, 7, 4);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);
        
        LC236lowest_common_ancestor_of_a_binary_tree test = new LC236lowest_common_ancestor_of_a_binary_tree();
        final TreeNode result = test.lowestCommonAncestor(root, new TreeNode(5), new TreeNode(1));
        System.out.println(result.val);
    }

}
