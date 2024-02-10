package com.hz.yk.base.algo.hot100.practice6;

/**
 * https://leetcode.cn/problems/longest-valid-parentheses/description/
 * 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度
 *
 * @author wuzheng.yk
 * @date 2024/2/7
 */
public class LC032longest_valid {

    /**
     * ()()
     * (())
     *
     * @param s
     * @return
     */
    public int longestValidParentheses(String s) {
        final int len = s.length();
        if (len < 2) {
            return 0;
        }
        //表示当前下标往前能匹配的最长有效括号数
        int[] dp = new int[len];
        int max = 0;
        for (int i = 1; i < len; i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = (i > 1 ? dp[i - 2] : 0) + 2;
                } else {
                    //有可能跟i配对的位置
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
