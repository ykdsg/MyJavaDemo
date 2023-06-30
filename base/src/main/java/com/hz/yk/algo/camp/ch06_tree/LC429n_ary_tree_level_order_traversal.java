package com.hz.yk.algo.camp.ch06_tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

/**
 * @author wuzheng.yk
 * @date 2023/6/30
 */
public class LC429n_ary_tree_level_order_traversal {

    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> result = new LinkedList<>();
        if (root == null) {
            return result;
        }
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> valList = new ArrayList<>();
            //这样可以当前层node 的个数，不会取到下层加入queue的数据
            final int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                final Node node = queue.poll();
                valList.add(node.val);
                Optional.ofNullable(node.children).orElse(new ArrayList<>()).forEach(t -> queue.offer(t));

            }
            result.add(valList);
        }
        return result;
    }

    public List<List<Integer>> levelOrder2(Node root) {
        List<List<Integer>> result = new ArrayList<>();
        level(root, 0, result);
        return result;
    }

    private void level(Node node, int level, List<List<Integer>> result) {
        if (node == null) {
            return;
        }
        if (level >= result.size()) {
            result.add(new ArrayList<>());
        }
        final List<Integer> valList = result.get(level);
        valList.add(node.val);
        for (Node child : node.children) {
            level(child, level + 1, result);
        }
    }

}
