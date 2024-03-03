package com.hz.yk.base.algo.hot100.practice1;

/**
 * https://leetcode.cn/problems/longest-valid-parentheses/description/
 * 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度
 *
 * @author wuzheng.yk
 * @date 2024/3/3
 */
public class LC032longest_valid {

    /**
     * (()))
     * ))))
     *
     * @param s
     * @return
     */
    public int longestValidParentheses(String s) {
        final int len = s.length();
        if (len < 2) {
            return 0;
        }
        int[] dp = new int[len];
        int max = 0;
        for (int i = 1; i < len; i++) {
            // 只有右括号才需要判断是否能构成闭合
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = (i < 2 ? 0 : dp[i - 2]) + 2;
                } else { // 上一个也是右括号的情况
                    //需要看跟i-1 匹配的数量之前的那个位置是否有个左括号进行匹配
                    final int pre = i - dp[i - 1] - 1;
                    if (pre >= 0 && s.charAt(pre) == '(') { // 如果前面刚好有匹配的左括号
                        //说明可以可以刚好可以连一起，需要再看pre-1 是否存在有效括号数
                        dp[i] = (pre > 0 ? dp[pre - 1] : 0) + dp[i - 1] + 2;
                    }
                }
                max = Math.max(max, dp[i]);

            }
        }
        return max;
    }
}
