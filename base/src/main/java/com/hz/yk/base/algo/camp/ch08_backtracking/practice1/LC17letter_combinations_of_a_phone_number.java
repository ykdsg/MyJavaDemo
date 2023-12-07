package com.hz.yk.base.algo.camp.ch08_backtracking.practice1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 *
 * https://leetcode.cn/problems/letter-combinations-of-a-phone-number/
 * @author wuzheng.yk
 * @date 2023/8/9
 */
public class LC17letter_combinations_of_a_phone_number {
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

    public List<String> letterCombinations(String digits) {

        return null;
    }
}
