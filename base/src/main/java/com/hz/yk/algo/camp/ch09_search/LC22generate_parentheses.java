package com.hz.yk.algo.camp.ch09_search;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 * @author wuzheng.yk
 * @date 2023/7/27
 */
public class LC22generate_parentheses {
    //没有递归写法（深度遍历DFS）那么简便
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        if (n <= 0) {
            return result;
        }
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(new Node("", n, n));
        while (!queue.isEmpty()) {
            final Node node = queue.poll();
            if( node.left == 0 && node.right == 0){
                result.add(node.str);
            }
            //左括号还有
            if (node.left > 0) {
                queue.offer(new Node(node.str + "(", node.left - 1, node.right));
            }
            //可以加右括号的情况，字符串中存在的左括号应该大于右括号，注意left 和right 是剩余可以用的数量，所以是反过来的逻辑
            if (node.right > 0 && node.left < node.right) {
                queue.offer(new Node(node.str + ")", node.left, node.right-1));
            }
        }
        return result;
    }
    
    static class Node{
        String str;
        int left;
        int right;

        public Node(String str, int left, int right) {
            this.str = str;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) {
        LC22generate_parentheses test = new LC22generate_parentheses();
        final List<String> result = test.generateParenthesis(3);
        System.out.println(result);
    }
}
