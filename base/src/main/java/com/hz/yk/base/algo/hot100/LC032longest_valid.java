package com.hz.yk.base.algo.hot100;

/**
 * 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度
 *
 * @author wuzheng.yk
 * @date 2024/1/29
 */
public class LC032longest_valid {

    public int longestValidParentheses(String s) {
        int max = 0;
        int[] dp = new int[s.length()];
        final char[] chars = s.toCharArray();
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] == ')') {
                if (chars[i - 1] == '(') {
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                } else if (i - dp[i - 1] > 0 && chars[i - dp[i - 1] - 1] == '(') {
                    dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;

                }
                max = Math.max(max, dp[i]);
            }
        }
        return max;
    }
}
