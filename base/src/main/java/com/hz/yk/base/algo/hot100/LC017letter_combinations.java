package com.hz.yk.base.algo.hot100;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * https://leetcode.cn/problems/letter-combinations-of-a-phone-number/description/
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 *
 * @author wuzheng.yk
 * @date 2024/1/24
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
        if (digits == null || Objects.equals("", digits)) {
            return result;
        }
        dsf(0, new StringBuilder(), digits);
        return result;
    }

    /**
     * 这里使用StringBuilder 会提升非常多，但是需要注意清理数据
     *
     * @param level
     * @param path
     * @param digits
     */
    private void dsf(int level, StringBuilder path, String digits) {
        //递归终止条件
        if (level >= digits.length()) {
            result.add(path.toString());
            return;
        }
        final String letter = cache.get(digits.charAt(level));
        for (char c : letter.toCharArray()) {
            dsf(level + 1, path.append(c), digits);
            //注意这里需要清理数据，这个跟直接使用string 作为参数不同
            path.deleteCharAt(path.length() - 1);
        }
    }
}
