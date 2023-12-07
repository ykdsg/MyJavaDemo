package com.hz.yk.base.algo.camp.ch12_dynamic;

/**
 * 给你两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少操作数  。
 * 你可以对一个单词进行如下三种操作：
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 *
 * https://leetcode.cn/problems/edit-distance/
 * @author wuzheng.yk
 * @date 2023/7/30
 */
public class LC27edit_distance {

    //核心是构造2维的状态表，行数是word1 的长度，列数是word2 的长度
    //如果word1[i]==word2[j]，那么dp[i][j]=dp[i-1][j-1]
    //否则 dp[i][j]=min(dp[i-1][j],dp[i][j-1],dp[i-1][j-1])+1
    //如果 dp[i][j-1] 最小那就插入一个字符
    //如果 dp[i-1][j] 最小那就删除一个字符
    //如果 dp[i][j] 最小那就替换一个字符
    // 整体思路想通之后是挺简单的，但是一开始确实不容易想到
    public int minDistance(String word1, String word2) {
        final int n = word1.length();
        final int m = word2.length();

        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++) {
            dp[i][0] = i;
        }
        for (int i = 0; i <= m; i++) {
            dp[0][i] = i;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (word1.charAt(i-1) == word2.charAt(j-1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                }else {
                    int preMin = Math.min(dp[i - 1][j - 1], dp[i][j - 1]);
                    preMin = Math.min(preMin, dp[i - 1][j]);
                    dp[i][j] = preMin + 1;
                }
            }
        }
        return dp[n][m];
    }

    public static void main(String[] args) {
        LC27edit_distance test = new LC27edit_distance();
        final int result = test.minDistance("horse", "ros");
        System.out.println(result);
    }
}
