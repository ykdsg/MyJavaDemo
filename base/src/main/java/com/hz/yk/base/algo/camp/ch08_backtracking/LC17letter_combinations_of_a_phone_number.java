package com.hz.yk.base.algo.camp.ch08_backtracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author wuzheng.yk
 * @date 2023/7/26
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

    List<String> result = new ArrayList<>();
    public List<String> letterCombinations(String digits) {
        if (digits == null || Objects.equals(digits, "")) {
            return result;
        }
        dsf(0, "", digits);
        return result;
    }

    void dsf(int level,String path, String digits) {
        if (level >= digits.length()) {
            result.add(path);
            return;
        }
        final String letter = cache.get(digits.charAt(level));
        for (char c : letter.toCharArray()) {
            dsf(level + 1, path + c, digits);
        }
    }

    public static void main(String[] args) {
        LC17letter_combinations_of_a_phone_number test = new LC17letter_combinations_of_a_phone_number();
        final List<String> result = test.letterCombinations("23");
        System.out.println(result);
    }


}
