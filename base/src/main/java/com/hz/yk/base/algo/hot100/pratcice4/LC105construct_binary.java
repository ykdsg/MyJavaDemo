package com.hz.yk.base.algo.hot100.pratcice4;

import com.hz.yk.base.algo.camp.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-inorder-traversal/description/
 * 给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。
 *
 * @author wuzheng.yk
 * @date 2024/3/29
 */
public class LC105construct_binary {

    /**
     * preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
     *
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        final int len = preorder.length;
        Map<Integer, Integer> cache = new HashMap<>();
        for (int i = 0; i < len; i++) {
            cache.put(inorder[i], i);
        }

        return dfs(preorder, 0, len, inorder, 0, len, cache);
    }

    private TreeNode dfs(int[] preorder, int preLeft, int preRight, int[] inorder, int inLeft, int inRight,
            Map<Integer, Integer> cache) {
        if (preLeft == preRight) {
            return null;
        }
        final int rootVal = preorder[preLeft];
        final int leftSize = cache.get(rootVal) - inLeft;
        final TreeNode root = new TreeNode(rootVal);

        root.left = dfs(preorder, preLeft + 1, preLeft + 1 + leftSize, inorder, inLeft, inLeft + leftSize, cache);
        root.right = dfs(preorder, preLeft + 1 + leftSize, preRight, inorder, inLeft + leftSize + 1, inRight, cache);
        return root;
    }

}
