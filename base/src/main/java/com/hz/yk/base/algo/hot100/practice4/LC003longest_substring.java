package com.hz.yk.base.algo.hot100.practice4;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.cn/problems/longest-substring-without-repeating-characters/description/
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度
 *
 * @author wuzheng.yk
 * @date 2024/1/28
 */
public class LC003longest_substring {

    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> cache = new HashMap<>();
        int left = 0, max = 0;
        final char[] charArray = s.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            if (cache.containsKey(charArray[i])) {
                //对left 的判断是关键
                left = Math.max(left, cache.get(charArray[i]) + 1);
            }
            cache.put(charArray[i], i);
            max = Math.max(max, i - left + 1);
        }

        return max;
    }
}
