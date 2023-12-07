package com.hz.yk.base.algo.camp.ch06_tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wuzheng.yk
 * @date 2023/6/30
 */
public class LC590n_ary_tree_postorder_traversal {
    public List<Integer> postorder(Node root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        if (root.children != null) {
            root.children.forEach( node -> {
                result.addAll(postorder(node));
            });
        }
        result.add(root.val);
        return result;
    }
}
