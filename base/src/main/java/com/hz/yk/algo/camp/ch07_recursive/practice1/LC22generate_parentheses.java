package com.hz.yk.algo.camp.ch07_recursive.practice1;

import java.util.ArrayList;
import java.util.List;

/**
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 * https://leetcode.cn/problems/generate-parentheses/
 * @author wuzheng.yk
 * @date 2023/8/9
 */
public class LC22generate_parentheses {

    List<String> result = new ArrayList<>();

    public List<String> generateParenthesis(int n) {

        dfs(0, n, 0, 0, "");
        return result;
    }

    void dfs(int level,int n, int left, int right, String path) {
        //递归终止条件
        if (level >= 2*n) {
            result.add(path);
            return;
        }
        //当前层要处理的逻辑
        if (left < n) {
            final String pathNew = path + "(";
            //下探到下一层
            dfs(level + 1, n, left + 1, right, pathNew);
        }
        if (left > right) {
            dfs(level + 1, n, left, right + 1, path + ")");
        }
    }

    public static void main(String[] args) {
        LC22generate_parentheses test = new LC22generate_parentheses();
        final List<String> result = test.generateParenthesis(3);
        System.out.println(result);
    }
}
