package com.hz.yk.base.algo.hot100.practice5;

/**
 * https://leetcode.cn/problems/longest-valid-parentheses/description/
 * 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度
 *
 * @author wuzheng.yk
 * @date 2024/2/6
 */
public class LC032longest_valid {

    public int longestValidParentheses(String s) {
        if (s.length() < 2) {
            return 0;
        }
        int[] dp = new int[s.length()];
        int max = 0;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                } else {
                    //i-1 是右括号的情况，需要看前面有没有可以配对的
                    final int pre = i - dp[i - 1] - 1;
                    if (pre >= 0 && s.charAt(pre) == '(') {
                        dp[i] = (pre > 0 ? dp[pre - 1] : 0) + dp[i - 1] + 2;
                    }
                }
                max = Math.max(max, dp[i]);
            }
        }
        return max;
    }

}
