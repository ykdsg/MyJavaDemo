package com.hz.yk.base.algo.hot100.practice5;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.cn/problems/longest-substring-without-repeating-characters/description/
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度
 *
 * @author wuzheng.yk
 * @date 2024/2/1
 */
public class LC003longest_substring {

    /**
     * 例子：abcabcbb
     *
     * @param s
     * @return
     */

    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> cache = new HashMap<>();
        //这里left 代表下一个有效字符串所在的位置
        int left = 0, max = 0;
        for (int i = 0; i < s.length(); i++) {
            if (cache.containsKey(s.charAt(i))) {
                //注意这里需要对cache获取的值+1
                left = Math.max(left, cache.get(s.charAt(i)) + 1);
            }
            cache.put(s.charAt(i), i);
            max = Math.max(max, i - left + 1);
        }
        return max;
    }

}
