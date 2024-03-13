package com.hz.yk.base.algo.hot100.practice2;

/**
 * https://leetcode.cn/problems/minimum-window-substring/description/
 * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 *
 * @author wuzheng.yk
 * @date 2024/3/12
 */
public class LC76minimum_window {

    public String minWindow(String s, String t) {
        int[] cache = new int[128];
        for (int i = 0; i < t.length(); i++) {
            cache[t.charAt(i)]++;
        }
        int cnt = t.length();
        int begin = 0, min = 0;
        for (int left = 0, right = 0; right < s.length(); right++) {
            if (cache[s.charAt(right)] > 0) {
                cnt--;
            }
            cache[s.charAt(right)]--;
            //说明完全包含t
            if (cnt == 0) {
                while (cache[s.charAt(left)] < 0) {
                    cache[s.charAt(left)]++;
                    left++;
                }
                int len = right - left + 1;
                if (min == 0 || len < min) {
                    min = len;
                    begin = left;
                }
                cache[s.charAt(left)]++;
                cnt++;
                left++;
            }
        }

        return s.substring(begin, begin + min);
    }

}
