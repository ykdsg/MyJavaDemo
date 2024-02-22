package com.hz.yk.base.algo.hot100;

/**
 * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 *
 * @author wuzheng.yk
 * @date 2024/2/21
 */
public class LC76minimum_window {

    public String minWindow(String s, String t) {
        //因为只包含英文，这里直接使用asc 数组来表示，比用map 更方便操作
        int[] need = new int[128];
        for (int i = 0; i < t.length(); i++) {
            need[t.charAt(i)]++;
        }
        int min = 0, begin = 0;
        int needCnt = t.length();
        for (int left = 0, right = 0; right < s.length(); right++) {
            if (need[s.charAt(right)] > 0) {
                needCnt--;
            }
            need[s.charAt(right)]--;

            //满足条件的情况
            if (needCnt == 0) {
                while (need[s.charAt(left)] < 0) { // 存在多余的情况
                    need[s.charAt(left)]++;
                    left++;
                } //一直缩小范围到不能少
                final int len = right - left + 1;
                // min 跟begin 是联动的，必须通过if判断
                if (min == 0 || min > len) {
                    min = len;
                    begin = left;
                }

                // 滑动窗口的左边接着往右移动，因此原来符合条件的情况要调整
                need[s.charAt(left)]++;
                needCnt++;
                left++;
            }
        }
        return s.substring(begin, begin + min);
    }

    public static void main(String[] args) {
        LC76minimum_window test = new LC76minimum_window();
        final String s = test.minWindow("ADOBECODEBANC", "ABC");
        System.out.println(s);
    }
}
