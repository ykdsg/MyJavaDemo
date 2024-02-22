package com.hz.yk.base.algo.hot100;

/**
 * https://leetcode.cn/problems/edit-distance/description/
 * 给你两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少操作数  。
 * 你可以对一个单词进行如下三种操作：
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 *
 * @author wuzheng.yk
 * @date 2024/2/21
 */
public class LC72edit_distance {

    /**
     * 当 word1[i] == word2[j]，dp[i][j] = dp[i-1][j-1]；
     * 当 word1[i] != word2[j]，dp[i][j] = min(dp[i-1][j-1], dp[i-1][j], dp[i][j-1]) + 1
     * 其中，dp[i-1][j-1] 表示替换操作，dp[i-1][j] 表示删除操作，dp[i][j-1] 表示插入操作。
     *
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {
        final int row = word1.length();
        final int col = word2.length();
        // 因为有初始行和初始列，所以这里长度+1
        int[][] dp = new int[row + 1][col + 1];
        for (int i = 0; i <= row; i++) {
            dp[i][0] = i;
        }
        for (int i = 0; i <= col; i++) {
            dp[0][i] = i;
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (word1.charAt(i) == word2.charAt(j)) {
                    dp[i + 1][j + 1] = dp[i][j];
                } else {
                    dp[i + 1][j + 1] = Math.min(Math.min(dp[i][j], dp[i][j + 1]), dp[i + 1][j]) + 1;
                }

            }
        }
        return dp[row][col];
    }

}
