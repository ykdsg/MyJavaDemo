package com.hz.yk.base.algo.hot100.practice8;

/**
 * https://leetcode.cn/problems/longest-valid-parentheses/description/
 * 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度
 *
 * @author wuzheng.yk
 * @date 2024/2/21
 */
public class LC032longest_valid {

    /**
     * ((())
     *
     * @param s
     * @return
     */
    public int longestValidParentheses(String s) {
        int[] dp = new int[s.length()];
        int max = 0;
        final char[] chars = s.toCharArray();
        for (int i = 1; i < chars.length; i++) {
            //只需要判断右括号的情况，只有这样才有可能形成有效括号
            if (chars[i] == ')') {
                //跟前一个刚好配对
                if (chars[i - 1] == '(') {
                    dp[i] = (i > 2 ? dp[i - 2] : 0) + 2;
                } else {
                    //前一个也是右括号，那就需要判断下前面有没有可以配对的左括号
                    int pre = i - 1 - dp[i - 1];
                    if (pre >= 0 && chars[pre] == '(') {
                        dp[i] = (pre > 1 ? dp[pre - 1] : 0) + dp[i - 1] + 2;
                    }
                }
                if (dp[i] > 0) {
                    max = Math.max(max, dp[i]);
                }
            }
        }
        return max;
    }
}
