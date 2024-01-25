package com.hz.yk.base.algo.hot100.practice2;

/**
 * https://leetcode.cn/problems/longest-palindromic-substring/description/
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 * 如果字符串的反序与原始字符串相同，则该字符串称为回文字符串。
 *
 * @author wuzheng.yk
 * @date 2024/1/25
 */
public class LC005longest_palindromic {

    /**
     * 使用动态规划的思想，dp[i][j] 表示下标i到j是否为回文
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        final int len = s.length();
        if (len < 2) {
            return s;
        }
        int begin = 0, max = 1;
        boolean[][] dp = new boolean[len][len];
        final char[] charArray = s.toCharArray();
        for (int j = 0; j < len; j++) {
            //注意这里i<=j，有个边界情况i=j，单独一个字符也是回文应该为true
            for (int i = 0; i <= j; i++) {
                if (charArray[i] == charArray[j]) {
                    if (j - i < 3) {
                        //长度3以内，2头字符相等就可以判断是回文
                        dp[i][j] = true;
                    } else {
                        //长度大于3，需要看内部是否回文
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                    if (dp[i][j] && (j - i + 1) > max) {
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
        final String result = test.longestPalindrome("dd");
        System.out.println(result);
    }
}
