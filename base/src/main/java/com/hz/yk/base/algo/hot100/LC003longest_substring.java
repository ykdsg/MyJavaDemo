package com.hz.yk.base.algo.hot100;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度
 *
 * @author wuzheng.yk
 * @date 2024/1/20
 */
public class LC003longest_substring {

    /**
     * 使用滑动窗口的思想，维护left 下标，遇到重复的就更新left的位置
     * 这里使用数组代替map理论上会更快
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
                // 这里+1 说明遇到重复字母，left 标往前挪动了一位
                left = Math.max(left, cache.get(key) + 1);
            }
            cache.put(key, i);
            max = Math.max(max, i - left + 1);
        }
        return max;
    }
}
