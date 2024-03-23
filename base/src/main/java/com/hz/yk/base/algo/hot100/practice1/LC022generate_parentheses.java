package com.hz.yk.base.algo.hot100.practice1;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.cn/problems/generate-parentheses/description/
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 *
 * @author wuzheng.yk
 * @date 2024/3/23
 */
public class LC022generate_parentheses {

    List<String> result = new ArrayList<>();

    public List<String> generateParenthesis(int n) {
        dfs("", n, n);
        return result;
    }

    private void dfs(String path, int left, int right) {
        if (left == 0 && right == 0) {
            result.add(path);
        }
        if (left > right) {
            return;
        }
        if (left > 0) {
            dfs(path + "(", left - 1, right);
        }
        if (right > 0) {
            dfs(path + ")", left, right - 1);
        }
    }

}
