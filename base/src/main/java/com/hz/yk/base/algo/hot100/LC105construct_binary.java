package com.hz.yk.base.algo.hot100;

import com.hz.yk.base.algo.camp.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。
 *
 * @author wuzheng.yk
 * @date 2024/3/8
 */
public class LC105construct_binary {

    /**
     * 前序遍历：3,9,20,15,7
     * 中序遍历：9,3,15,20,7
     * 前序遍历中的3表示root， 在中序遍历中的左边都是左子树，右边都是右子树
     *
     * @param preorder
     * @param inorder
     * @return
     */

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        Map<Integer, Integer> cache = new HashMap<>();
        for (int i = 0; i < n; i++) {
            cache.put(inorder[i], i);
        }
        return dfs(preorder, 0, n, inorder, 0, n, cache); // 左闭右开区间

    }

    private TreeNode dfs(int[] preorder, int preL, int preR, int[] inorder, int inL, int inR,
            Map<Integer, Integer> inOrderCache) {
        if (preL == preR) {
            return null;
        }
        int leftSize = inOrderCache.get(preorder[preL]) - inL; // 左子树大小
        final TreeNode leftNode = dfs(preorder, preL + 1, preL + 1 + leftSize, inorder, inL, inL + leftSize,
                                      inOrderCache);
        final TreeNode rightNode = dfs(preorder, preL + 1 + leftSize, preR, inorder, inL + 1 + leftSize, inR,
                                       inOrderCache);

        return new TreeNode(preorder[preL], leftNode, rightNode);
    }
}
