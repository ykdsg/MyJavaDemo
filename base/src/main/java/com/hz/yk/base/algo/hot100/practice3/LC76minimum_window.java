package com.hz.yk.base.algo.hot100.practice3;

/**
 * https://leetcode.cn/problems/minimum-window-substring/description/
 * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 *
 * @author wuzheng.yk
 * @date 2024/4/26
 */
public class LC76minimum_window {

    public String minWindow(String s, String t) {
        int[] cache = new int[128];
        for (int i = 0; i < t.length(); i++) {
            cache[t.charAt(i)]++;
        }
        int cnt = t.length();
        int begin = 0, min = s.length() + 1;
        for (int l = 0, r = 0; r < s.length(); r++) {
            if (cache[s.charAt(r)] > 0) {
                cnt--;
            }
            cache[s.charAt(r)]--;
            if (cnt == 0) { // 说明涵盖了字符串t
                while (cache[s.charAt(l)] < 0) { // left 游标往右滑动
                    cache[s.charAt(l)]++;
                    l++;
                }
                int len = r - l + 1;
                if (len < min) {
                    begin = l;
                    min = len;
                }
                // left 往前走1步进行下一轮判断，这个时候要补充对应的数据
                cache[s.charAt(l)]++;
                cnt++;
                l++;
            }
        }

        return min > s.length() ? "" : s.substring(begin, begin + min);
    }

}
