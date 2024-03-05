package com.hz.yk.base.algo.hot100.practice1;

import java.util.HashMap;

/**
 * https://leetcode.cn/problems/longest-substring-without-repeating-characters/description/
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度
 *
 * @author wuzheng.yk
 * @date 2024/3/5
 */
public class LC003longest_substring {

    /**
     * 使用滑动窗口，维护一个左游标，注意左游标每次迭代的位置只能往前
     * abca
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> cache = new HashMap<>();
        int left = 0, max = 0;
        for (int i = 0; i < s.length(); i++) {
            if (cache.containsKey(s.charAt(i))) {
                //注意这里始终要取最大值，因为左游标不可能开倒车
                left = Math.max(left, cache.get(s.charAt(i)) + 1);
            }
            max = Math.max(max, i - left + 1);
            cache.put(s.charAt(i), i);
        }
        return max;
    }

    public static void main(String[] args) {
        LC003longest_substring test = new LC003longest_substring();
        final int result = test.lengthOfLongestSubstring("abba");
        System.out.println(result);

    }
}
