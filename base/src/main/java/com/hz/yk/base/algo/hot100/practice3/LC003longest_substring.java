package com.hz.yk.base.algo.hot100.practice3;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.cn/problems/longest-substring-without-repeating-characters/description/
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度
 *
 * @author wuzheng.yk
 * @date 2024/1/25
 */
public class LC003longest_substring {

    /**
     * 滑动窗口思路，维护一个左边界left，右边界是当前索引下标i
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> cache = new HashMap<>();
        int left = 0, max = 0;
        for (int i = 0; i < s.length(); i++) {
            final char key = s.charAt(i);
            if (cache.containsKey(key)) {
                // 注意这里的+1
                left = Math.max(left, cache.get(key) + 1);
            }
            max = Math.max(max, i - left + 1);
            cache.put(key, i);
        }
        return max;
    }

    public static void main(String[] args) {
        LC003longest_substring test = new LC003longest_substring();
        final int result = test.lengthOfLongestSubstring("abcabcbb");
        System.out.println(result);
    }
}
