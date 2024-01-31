package com.hz.yk.base.algo.hot100.practice4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.cn/problems/letter-combinations-of-a-phone-number/description/
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 *
 * @author wuzheng.yk
 * @date 2024/1/30
 */
public class LC017letter_combinations {

    static Map<Character, String> cache = new HashMap<>();

    static {
        cache.put('2', "abc");
        cache.put('3', "def");
        cache.put('4', "ghi");
        cache.put('5', "jkl");
        cache.put('6', "mno");
        cache.put('7', "pqrs");
        cache.put('8', "tuv");
        cache.put('9', "wxyz");
    }

    List<String> result = new ArrayList<>();

    public List<String> letterCombinations(String digits) {
        if (null == digits || digits.isEmpty()) {
            return result;
        }

        dsf("", 0, digits);
        return result;
    }

    private void dsf(String path, int level, String digits) {
        if (level >= digits.length()) {
            result.add(path);
            return;
        }
        final String str = cache.get(digits.charAt(level));
        for (int i = 0; i < str.length(); i++) {
            dsf(path + str.charAt(i), level + 1, digits);
        }
    }

}
