package com.hz.yk.base.algo.hot100.practice6;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.cn/problems/letter-combinations-of-a-phone-number/description/
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 *
 * @author wuzheng.yk
 * @date 2024/3/1
 */
public class LC017letter_combinations {

    String[] mapping = { "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };

    List<String> result = new ArrayList<>();
    char[] path;

    public List<String> letterCombinations(String digits) {
        if (digits.isEmpty()) {
            return result;
        }
        path = new char[digits.length()];
        dfs(digits, 0);
        return result;
    }

    private void dfs(String digits, int level) {
        if (level == digits.length()) {
            result.add(new String(path));
            return;
        }
        final String str = mapping[digits.charAt(level) - '0'];
        for (char c : str.toCharArray()) {
            path[level] = c;
            dfs(digits, level + 1);
        }
    }
}
