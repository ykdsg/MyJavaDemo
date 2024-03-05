package com.hz.yk.base.algo.hot100.practice1;

/**
 * https://leetcode.cn/problems/longest-palindromic-substring/description/
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 * 如果字符串的反序与原始字符串相同，则该字符串称为回文字符串。
 *
 * @author wuzheng.yk
 * @date 2024/3/4
 */
public class LC005longest_palindromic {

    /**
     * sas
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        final int n = s.length();
        //dp[i][j] 表示s[i][j] 是否是回文
        boolean[][] dp = new boolean[n][n];
        int begin = 0, max = 1;
        for (int right = 1; right < n; right++) {
            for (int left = 0; left < right; left++) {
                if (s.charAt(left) == s.charAt(right)) {
                    if (right - left < 3) {
                        dp[left][right] = true;
                    } else {
                        dp[left][right] = dp[left + 1][right - 1];
                    }
                    if (dp[left][right]) {
                        final int len = right - left + 1;
                        if (len > max) {
                            max = len;
                            begin = left;
                        }
                    }
                }
            }
        }
        return s.substring(begin, begin + max);
    }

}
