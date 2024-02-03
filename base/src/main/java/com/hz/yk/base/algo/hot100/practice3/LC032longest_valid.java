package com.hz.yk.base.algo.hot100.practice3;

/**
 * https://leetcode.cn/problems/longest-valid-parentheses/description/
 * 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度
 *
 * @author wuzheng.yk
 * @date 2024/2/1
 */
public class LC032longest_valid {

    /**
     * (()())
     * )))
     *
     * @param s
     * @return
     */
    public int longestValidParentheses(String s) {
        final char[] chars = s.toCharArray();
        //动态数组，存放当前下标存在的最长有效子串长度
        int[] dp = new int[chars.length];
        int max = 0;
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] == ')') {
                //如果刚好能配对，数量相对于i-2 存在的有效数+ 当前配对的2个
                if (chars[i - 1] == '(') {
                    dp[i] = (i > 2 ? dp[i - 2] : 0) + 2;
                } else {
                    //  i-1 是) 右括号，就看前面是否存在多余可以匹配的左括号
                    final int pre = i - dp[i - 1] - 1;
                    if (pre >= 0 && chars[pre] == '(') {
                        dp[i] = (pre > 0 ? dp[pre - 1] : 0) + dp[i - 1] + 2;
                    }
                }
                max = Math.max(max, dp[i]);
            }
        }
        return max;
    }

}
