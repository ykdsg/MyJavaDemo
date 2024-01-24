package com.hz.yk.base.algo.hot100.practice1;

/**
 * https://leetcode.cn/problems/longest-palindromic-substring/description/
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 * 如果字符串的反序与原始字符串相同，则该字符串称为回文字符串。
 *
 * @author wuzheng.yk
 * @date 2024/1/24
 */
public class LC005longest_palindromic {

    /**
     * 动态规划的思路,dp[i][j] 表示下标i到j 字符串是否回文
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        final int len = s.length();
        if (len < 2) {
            return s;
        }
        boolean[][] dp = new boolean[len][len];
        final char[] charArray = s.toCharArray();
        int begin = 0, max = 1;
        for (int j = 0; j < len; j++) {
            for (int i = 0; i <= j; i++) {
                if (charArray[j] == charArray[i]) {
                    //如果间隔小于3，可以直接判断为回文
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        //否则需要进一步判断内部是否为回文
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }
                if (dp[i][j] && (j - i + 1) > max) {
                    max = j - i + 1;
                    begin = i;
                }
            }
        }

        return s.substring(begin, begin + max);
    }
}
