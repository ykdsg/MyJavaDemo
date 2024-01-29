package com.hz.yk.base.algo.hot100.practice3;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.cn/problems/generate-parentheses/description/
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 *
 * @author wuzheng.yk
 * @date 2024/1/29
 */
public class LC022generate_parentheses {

    List<String> result = new ArrayList<>();

    public List<String> generateParenthesis(int n) {
        if (n <= 0) {
            return result;
        }
        dsf("", n, n);
        return result;
    }

    private void dsf(String path, int left, int right) {
        //剩余左括号数据比右括号多，说明现有path中右括号数量大于左括号，这个明显是错误的
        if (left > right) {
            return;
        }
        if (left == 0 && right == 0) {
            result.add(path);
            return;
        }
        if (left > 0) {
            dsf(path + "(", left - 1, right);
        }
        if (right > 0) {
            dsf(path + ")", left, right - 1);
        }
    }
}
