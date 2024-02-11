package com.hz.yk.base.algo.hot100.practice5;

/**
 * https://leetcode.cn/problems/longest-palindromic-substring/description/
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 * 如果字符串的反序与原始字符串相同，则该字符串称为回文字符串。
 *
 * @author wuzheng.yk
 * @date 2024/2/10
 */
public class LC005longest_palindromic {

    public String longestPalindrome(String s) {
        final int len = s.length();
        if (len < 2) {
            return s;
        }
        //dp[i][j] 表示 i到j能构成的回文字数，默认为0
        boolean[][] dp = new boolean[len][len];
        int max = 0, begin = 0;
        for (int j = 0; j < len; j++) {
            for (int i = 0; i <= j; i++) {
                if (s.charAt(j) == s.charAt(i)) {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                    if (dp[i][j] && j - i + 1 > max) {
                        max = j - i + 1;
                        begin = i;
                    }
                }
            }
        }
        return s.substring(begin, begin + max);

    }

    public static void main(String[] args) {
        LC005longest_palindromic test = new LC005longest_palindromic();
        final String result = test.longestPalindrome("ac");
        System.out.println(result);
    }

}
