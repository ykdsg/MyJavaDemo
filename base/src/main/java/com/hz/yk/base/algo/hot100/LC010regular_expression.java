package com.hz.yk.base.algo.hot100;

/**
 * https://leetcode.cn/problems/regular-expression-matching/description/
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
 *
 * @author wuzheng.yk
 * @date 2024/3/2
 */
public class LC010regular_expression {

    public boolean isMatch(String s, String p) {
        final int row = s.length();
        final int col = p.length();

        //dp[i][j]表示 s的前i个字符跟p的前j个字符是否能够匹配
        boolean[][] dp = new boolean[row + 1][col + 1];
        dp[0][0] = true;
        for (int i = 0; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i][j - 2]; //*匹配0次的情况，相当于* 和*之前的字符都删除的情况
                    if (matches(s, p, i, j - 1)) {
                        dp[i][j] = dp[i][j] || dp[i - 1][j]; //*匹配1次或多次的情况
                    }
                } else if (matches(s, p, i, j)) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
            }
        }
        return dp[row][col];
    }

    /**
     * 判断 s[i] 跟p[j] 是否能匹配，不包含*
     *
     * @param s
     * @param p
     * @param i
     * @param j
     * @return
     */
    private boolean matches(String s, String p, int i, int j) {
        if (i == 0) { //s[i]为空，p[j]不为* 的情况下，不可能匹配
            return false;
        }
        if (p.charAt(j - 1) == '.') {
            return true;
        }
        return s.charAt(i - 1) == p.charAt(j - 1);
    }
}
