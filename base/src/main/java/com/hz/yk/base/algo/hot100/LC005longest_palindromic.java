package com.hz.yk.base.algo.hot100;

/**
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 * 如果字符串的反序与原始字符串相同，则该字符串称为回文字符串。
 *
 * @author wuzheng.yk
 * @date 2024/1/23
 */
public class LC005longest_palindromic {

    public String longestPalindrome(String s) {
        final int len = s.length();
        if (len < 2) {
            return s;
        }
        int begin = 0, max = 1;

        boolean[][] dp = new boolean[len][len];
        final char[] charArray = s.toCharArray();
        for (int j = 0; j < len; j++) {
            for (int i = 0; i <= j; i++) {
                if (charArray[j] == charArray[i]) {
                    //只有3个的时候，只要两边相等就是回文
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        //大于3个的时候就要看进一步的情况
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }
                if (dp[i][j] && j - i + 1 > max) {
                    begin = i;
                    max = j - i + 1;
                }
            }
        }
        return s.substring(begin, begin + max);
    }

}
