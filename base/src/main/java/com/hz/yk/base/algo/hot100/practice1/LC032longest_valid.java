package com.hz.yk.base.algo.hot100.practice1;

/**
 * https://leetcode.cn/problems/longest-valid-parentheses/description/
 * 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度
 *
 * @author wuzheng.yk
 * @date 2024/1/30
 */
public class LC032longest_valid {

    public int longestValidParentheses(String s) {
        final char[] chars = s.toCharArray();
        //dp[i] 表示当前这个位置往前存在多少个有效括号长度
        int[] dp = new int[chars.length];
        int max = 0;
        for (int i = 1; i < chars.length; i++) {
            //遇到闭合的右括号才有可能存在有效的括号
            if (chars[i] == ')') {
                if (chars[i - 1] == '(') { // 如果刚好跟前一位配对，那就再看前2位是否存在有效数量
                    dp[i] = (i > 2 ? dp[i - 2] : 0) + 2;
                } else {
                    //如果前一位也是右括号，就要看有效数量之前是否存在配对的可能
                    int pre = i - dp[i - 1];
                    //如果能跟前面的配对上
                    if (pre > 0 && chars[pre - 1] == '(') {
                        dp[i] = (pre >= 2 ? dp[pre - 2] : 0) + dp[i - 1] + 2;
                    }
                }
                max = Math.max(max, dp[i]);
            }
        }

        return max;
    }

}
