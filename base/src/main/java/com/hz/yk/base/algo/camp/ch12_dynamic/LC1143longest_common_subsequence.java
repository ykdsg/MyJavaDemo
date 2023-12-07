package com.hz.yk.base.algo.camp.ch12_dynamic;

/**
 * 给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
 *
 * @author wuzheng.yk
 * @date 2023/7/27
 */
public class LC1143longest_common_subsequence {
    //核心是能否找到状态表和递推公式
    public int longestCommonSubsequence(String text1, String text2) {
        final int m = text1.length() + 1;
        final int n = text2.length() + 1;
        //中间状态表，这里有讲究，为了方便初始化第0排，第0列 ，因此整体扩1位
        //相当于表示一个text跟什么都没有的空串的公共子序列为0
        int[][] dp = new int[m][n];
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                //下面就是核心的递推方程
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    public static void main(String[] args) {
        LC1143longest_common_subsequence test = new LC1143longest_common_subsequence();
        final int result = test.longestCommonSubsequence("abcde", "ace");
        System.out.println(result);

    }
}
