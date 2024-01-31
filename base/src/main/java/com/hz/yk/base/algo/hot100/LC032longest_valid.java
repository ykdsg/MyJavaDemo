package com.hz.yk.base.algo.hot100;

/**
 * https://leetcode.cn/problems/longest-valid-parentheses/description/
 * 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度
 *
 * @author wuzheng.yk
 * @date 2024/1/29
 */
public class LC032longest_valid {

    public int longestValidParentheses(String s) {
        int max = 0;
        //dp[i] 表示以下标 i 字符结尾的最长有效括号的长度
        int[] dp = new int[s.length()];
        final char[] chars = s.toCharArray();
        for (int i = 1; i < chars.length; i++) {
            //最后结尾是右括号才有可能是有效的
            if (chars[i] == ')') {
                //如果前一位刚好是左括号刚好凑成一对，那就看dp[i-2] 的数量
                if (chars[i - 1] == '(') {
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                } else {
                    //前一个匹配不上的位置
                    final int preIndex = i - dp[i - 1];
                    //看下前面是否刚好有一个左括号来配对
                    if (preIndex > 0 && chars[preIndex - 1] == '(') {
                        //因为dp[preIndex-1] 刚好连接上了，所以要接上 dp[preIndex - 2]
                        dp[i] = dp[i - 1] + (preIndex >= 2 ? dp[preIndex - 2] : 0) + 2;
                    }
                }
                max = Math.max(max, dp[i]);
            }
        }
        return max;
    }
}
