package com.hz.yk.base.algo.hot100.practice3;

/**
 * https://leetcode.cn/problems/regular-expression-matching/description/
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
 *
 * @author wuzheng.yk
 * @date 2024/4/26
 */
public class LC010regular_expression {

    public boolean isMatch(String s, String p) {
        final int m = s.length(), n = p.length();
        //dp[i][j] 表示s前i个字符跟p前j个字符是否匹配
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int i = 0; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i][j - 2]; // * 匹配0个前面元素的情况
                    if (matchesWithoutAsterisk(s, p, i, j - 1)) {
                        dp[i][j] = dp[i][j] || dp[i - 1][j]; // * 匹配1个或多个前面元素的情况
                    }
                } else if (matchesWithoutAsterisk(s, p, i, j)) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
            }
        }
        return dp[m][n];
    }

    /**
     * 除* 外的匹配情况，注意是第i个，第j个。转换成下标的话需要-1
     *
     * @param s
     * @param p
     * @param i
     * @param j
     * @return
     */
    private boolean matchesWithoutAsterisk(String s, String p, int i, int j) {
        if (i == 0) {
            return false;
        }
        if (p.charAt(j - 1) == '.') {
            return true;
        }
        return s.charAt(i - 1) == p.charAt(j - 1);
    }

}
