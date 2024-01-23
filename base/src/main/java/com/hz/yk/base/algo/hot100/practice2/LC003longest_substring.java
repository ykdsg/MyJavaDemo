package com.hz.yk.base.algo.hot100.practice2;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wuzheng.yk
 * @date 2024/1/23
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
        int max = 0;
        int left = 0;
        for (int i = 0; i < s.length(); i++) {
            final char key = s.charAt(i);
            if (cache.containsKey(key)) {
                left = Math.max(left, cache.get(key) + 1);
            }
            cache.put(key, i);
            max = Math.max(max, i - left + 1);
        }

        return max;
    }
}
