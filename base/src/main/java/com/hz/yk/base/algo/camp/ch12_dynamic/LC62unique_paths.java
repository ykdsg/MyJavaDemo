package com.hz.yk.base.algo.camp.ch12_dynamic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
 * 问总共有多少条不同的路径？
 *
 * @author wuzheng.yk
 * @date 2023/10/18
 */
public class LC62unique_paths {

    /**
     * 递推公式：dp[i][j]=dp[i-1][j]+dp[i][j-1]
     * 因为只能从左边或者上边移动一格过来
     *
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        //初始化
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }
        // 递推公式
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    @Test
    public void test() {
        LC62unique_paths test = new LC62unique_paths();
        final int result28 = test.uniquePaths(7, 3);
        assertEquals(28, result28);
    }
}
