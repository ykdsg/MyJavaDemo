package com.hz.yk.base.algo.camp.ch06_tree;

import java.util.List;

/**
 * @author wuzheng.yk
 * @date 2023/6/30
 */
public class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
}
