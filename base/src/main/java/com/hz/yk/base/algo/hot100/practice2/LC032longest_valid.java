package com.hz.yk.base.algo.hot100.practice2;

/**
 * https://leetcode.cn/problems/longest-valid-parentheses/description/
 * 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度
 *
 * @author wuzheng.yk
 * @date 2024/1/31
 */
public class LC032longest_valid {

    public int longestValidParentheses(String s) {
        final char[] chars = s.toCharArray();
        int[] dp = new int[chars.length];
        int max = 0;
        for (int i = 1; i < chars.length; i++) {
            //只有封闭的右括号才有可能配对
            if (chars[i] == ')') {
                //跟前一位刚好匹配
                if (chars[i - 1] == '(') {
                    // 数量就是前前位+2，这里>= 还是> 实际是一样的，因为dp[0]=0
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                } else { // i-1 也是右括号
                    final int pre = i - 1 - dp[i - 1];
                    if (pre >= 0 && chars[pre] == '(') {
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
        final int i = test.longestValidParentheses("(()");
    }
}
