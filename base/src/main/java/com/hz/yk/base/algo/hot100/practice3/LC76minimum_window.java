package com.hz.yk.base.algo.hot100.practice3;

/**
 * https://leetcode.cn/problems/minimum-window-substring/description/
 * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 *
 * @author wuzheng.yk
 * @date 2024/3/24
 */
public class LC76minimum_window {

    public String minWindow(String s, String t) {
        int[] cache = new int[128];
        for (int i = 0; i < t.length(); i++) {
            cache[t.charAt(i)]++;
        }
        int cnt = t.length();
        int min = 0, begin = 0;
        for (int right = 0, left = 0; right < s.length(); right++) {
            if (cache[s.charAt(right)] > 0) {
                cnt--;
            }
            cache[s.charAt(right)]--;
            if (cnt == 0) {
                //去掉多余的字符
                while (cache[s.charAt(left)] < 0) {
                    cache[s.charAt(left)]++;
                    left++;
                }
                final int len = right - left + 1;
                if (min == 0 || len < min) {
                    min = len;
                    begin = left;
                }
                // 开始新的阶段，left 需要往前一步
                cache[s.charAt(left)]++;
                left++;
                cnt++;
            }
        }
        return s.substring(begin, begin + min);

    }

}
