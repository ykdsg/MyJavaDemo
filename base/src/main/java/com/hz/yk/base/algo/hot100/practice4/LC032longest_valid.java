package com.hz.yk.base.algo.hot100.practice4;

/**
 * https://leetcode.cn/problems/longest-valid-parentheses/description/
 * 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度
 *
 * @author wuzheng.yk
 * @date 2024/2/6
 */
public class LC032longest_valid {

    /**
     * (())()
     * ((()))
     *
     * @param s
     * @return
     */
    public int longestValidParentheses(String s) {
        int[] dp = new int[s.length()];
        int max = 0;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                //如果刚好跟前一个配对，数量就是i-2 能配对的数量+2
                if (s.charAt(i - 1) == '(') {
                    dp[i] = (i > 2 ? dp[i - 2] : 0) + 2;
                } else {
                    final int pre = i - dp[i - 1] - 1;
                    //如果前面刚好有左括号进行配对
                    if (pre >= 0 && s.charAt(pre) == '(') {
                        dp[i] = (pre > 0 ? dp[pre - 1] : 0) + dp[i - 1] + 2;
                    }
                }
                max = Math.max(max, dp[i]);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        LC032longest_valid test = new LC032longest_valid();
        String s = "()(())";
        final int result = test.longestValidParentheses(s);
        System.out.println(result);
    }
}
