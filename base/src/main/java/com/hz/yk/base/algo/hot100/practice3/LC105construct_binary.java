package com.hz.yk.base.algo.hot100.practice3;

import com.hz.yk.base.algo.camp.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-inorder-traversal/description/
 * 给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。
 *
 * @author wuzheng.yk
 * @date 2024/3/17
 */
public class LC105construct_binary {

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        final int len = preorder.length;
        Map<Integer, Integer> cache = new HashMap<>();
        for (int i = 0; i < len; i++) {
            cache.put(inorder[i], i);
        }

        return dfs(preorder, 0, len, inorder, 0, len, cache);//注意是左开右闭区间
    }

    //preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
    private TreeNode dfs(int[] preorder, int preL, int preR, int[] inorder, int inL, int inR,
            Map<Integer, Integer> cache) {
        if (preL == preR) {
            return null;
        }
        final int rootIndex = preorder[preL];
        final int leftSize = cache.get(rootIndex) - inL;
        final TreeNode root = new TreeNode(preorder[preL]);
        root.left = dfs(preorder, preL + 1, preL + 1 + leftSize, inorder, inL, inL + leftSize, cache);
        root.right = dfs(preorder, preL + 1 + leftSize, preR, inorder, inL + leftSize + 1, inR, cache);
        return root;
    }

}
