package com.hz.yk.base.algo.hot100.practice1;

/**
 * https://leetcode.cn/problems/minimum-window-substring/description/
 * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 *
 * @author wuzheng.yk
 * @date 2024/3/6
 */
public class LC76minimum_window {

    public String minWindow(String s, String t) {
        // 这里根据题目给出的限定条件，只有英文字母，所以可以使用128位asc 来表示
        int[] cache = new int[128];
        int cnt = t.length();
        for (int i = 0; i < t.length(); i++) {
            cache[t.charAt(i)]++;
        }
        int begin = 0, min = 0;
        for (int left = 0, right = 0; right < s.length(); right++) {
            if (cache[s.charAt(right)] > 0) {
                cnt--;
            }
            // 这里无差别-1，这样如果是负数就说明是t之外多余的
            cache[s.charAt(right)]--;
            if (cnt == 0) { //说明包含t
                while (cache[s.charAt(left)] < 0) { // left指针尽可能往右走
                    cache[s.charAt(left)]++;
                    left++;
                }
                final int len = right - left + 1;
                if (min == 0 || len < min) {
                    min = len;
                    begin = left;
                }

                //left 指针继续往前走，这个时候就破坏原来符合的情况了
                cache[s.charAt(left)]++;
                cnt++;
                left++;
            }
        }
        return s.substring(begin, begin + min);
    }

}
