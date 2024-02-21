package com.hz.yk.base.algo.hot100.practice6;

/**
 * https://leetcode.cn/problems/longest-palindromic-substring/description/
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 * 如果字符串的反序与原始字符串相同，则该字符串称为回文字符串。
 *
 * @author wuzheng.yk
 * @date 2024/2/20
 */
public class LC005longest_palindromic {

    public String longestPalindrome(String s) {
        final int len = s.length();
        if (len < 2) {
            return s;
        }
        //dp[i][j]表示i到j 构成的字符串是否回文
        boolean[][] dp = new boolean[len][len];
        int left = 0, max = 1;
        for (int j = 0; j < len; j++) {
            for (int i = 0; i < j; i++) {
                if (s.charAt(j) == s.charAt(i)) {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                    if (dp[i][j] && (j - i + 1) > max) {
                        max = j - i + 1;
                        left = i;
                    }
                }
            }
        }
        return s.substring(left, left + max);
    }
    
}
