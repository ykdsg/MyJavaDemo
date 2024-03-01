package com.hz.yk.base.algo.hot100.practice3;

/**
 * https://leetcode.cn/problems/edit-distance/description/
 * 给你两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少操作数  。
 * 你可以对一个单词进行如下三种操作：
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 *
 * @author wuzheng.yk
 * @date 2024/3/1
 */
public class LC72edit_distance {

    public int minDistance(String word1, String word2) {
        final int row = word1.length() + 1;
        final int col = word2.length() + 1;
        int[][] dp = new int[row][col];
        for (int i = 0; i < row; i++) {
            dp[i][0] = i;
        }
        for (int i = 0; i < col; i++) {
            dp[0][i] = i;
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i - 1][j - 1], dp[i][j - 1])) + 1;
                }
            }
        }
        return dp[row - 1][col - 1];
    }

}
