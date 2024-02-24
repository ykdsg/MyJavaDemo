package com.hz.yk.base.algo.hot100.practice1;

/**
 * https://leetcode.cn/problems/minimum-window-substring/description/
 * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 ""
 *
 * @author wuzheng.yk
 * @date 2024/2/23
 */
public class LC76minimum_window {

    public String minWindow(String s, String t) {
        int[] cache = new int[128];
        int cnt = t.length();
        int begin = 0, min = s.length() + 1;
        for (int i = 0; i < t.length(); i++) {
            cache[t.charAt(i)]++;
        }
        // 精髓就是这里的left，right 双游标
        for (int left = 0, right = 0; right < s.length(); right++) {
            if (cache[s.charAt(right)] > 0) {
                cnt--;
            }
            cache[s.charAt(right)]--;
            // 如果满足条件了
            if (cnt == 0) {
                //    这个时候需要看下是否可以将left 往右移动
                while (cache[s.charAt(left)] < 0) {
                    cache[s.charAt(left)]++;
                    left++;
                }
                int len = right - left + 1;
                //这里min 和 begin 是联动，因此要在一个if 判断块里面
                if (min > len) {
                    min = len;
                    begin = left;
                }
            }
        }
        return min > s.length() ? "" : s.substring(begin, begin + min);
    }

}
