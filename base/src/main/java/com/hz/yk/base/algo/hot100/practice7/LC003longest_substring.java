package com.hz.yk.base.algo.hot100.practice7;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.cn/problems/longest-substring-without-repeating-characters/description/
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度
 *
 * @author wuzheng.yk
 * @date 2024/2/15
 */
public class LC003longest_substring {

    /**
     * abcaeb
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> cache = new HashMap<>();
        int left = 0, max = 0;
        for (int i = 0; i < s.length(); i++) {
            if (cache.containsKey(s.charAt(i))) {
                left = Math.max(left, cache.get(s.charAt(i)) + 1);
            }
            cache.put(s.charAt(i), i);
            max = Math.max(max, i - left + 1);

        }
        return max;
    }
}
