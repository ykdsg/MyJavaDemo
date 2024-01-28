package com.hz.yk.base.algo.hot100;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.cn/problems/generate-parentheses/description/
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 *
 * @author wuzheng.yk
 * @date 2024/1/27
 */
public class LC022generate_parentheses {

    List<String> result = new ArrayList<>();

    public List<String> generateParenthesis(int n) {
        dsf("", n, n);
        return result;
    }

    /**
     * 另一种递归的写法
     *
     * @param path  当前的字符串
     * @param left  左括号剩余使用次数
     * @param right 右括号剩余使用次数
     */
    private void dsf(String path, int left, int right) {
        // 错误的情况
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

    /**
     * @param path  当前的字符串
     * @param left  左括号使用的次数
     * @param right 右括号使用的次数
     * @param n     括号的对数
     */
    private void dsf(String path, int left, int right, int n) {
        if (left == n && right == n) {
            result.add(path);
            return;
        }
        // 如果左括号多，那么这次可以再加上左括号或者右括号
        if (left > right) {
            if (left < n) {
                dsf(path + "(", left + 1, right, n);
            }
            dsf(path + ")", left, right + 1, n);

        } else {
            dsf(path + "(", left + 1, right, n);
        }
    }

}
