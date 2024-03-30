package com.hz.yk.base.algo.hot100.practice2;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.cn/problems/longest-substring-without-repeating-characters/description/
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度
 *
 * @author wuzheng.yk
 * @date 2024/3/30
 */
public class LC003longest_substring {

    /**
     * aabcabcbb
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> cache = new HashMap<>();
        int max = 0;
        for (int left = 0, right = 0; right < s.length(); right++) {
            if (cache.containsKey(s.charAt(right))) {
                left = Math.max(left, cache.get(s.charAt(right)) + 1);
            }
            cache.put(s.charAt(right), right);
            final int len = right - left + 1;
            max = Math.max(max, len);
        }
        return max;
    }

}
