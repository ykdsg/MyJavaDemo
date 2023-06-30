package com.hz.yk.algo.camp.ch06_tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wuzheng.yk
 * @date 2023/6/30
 */
public class LC94binary_tree_inorder_traversal {

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        result.addAll(inorderTraversal(root.left));
        result.add(root.val);
        result.addAll(inorderTraversal(root.right));
        
        return result;
    }

}
