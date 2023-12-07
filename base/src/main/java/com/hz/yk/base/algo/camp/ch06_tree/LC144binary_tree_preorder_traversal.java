package com.hz.yk.base.algo.camp.ch06_tree;

import com.hz.yk.base.algo.camp.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wuzheng.yk
 * @date 2023/6/30
 */
public class LC144binary_tree_preorder_traversal {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) { return result; }
        result.add(root.val);
        result.addAll(preorderTraversal(root.left));
        result.addAll(preorderTraversal(root.right));
        return result;
    }
}
