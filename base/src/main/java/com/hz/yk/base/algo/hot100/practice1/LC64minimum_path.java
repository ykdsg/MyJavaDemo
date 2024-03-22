package com.hz.yk.base.algo.hot100.practice1;

/**
 * https://leetcode.cn/problems/minimum-path-sum/description/
 * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小
 * 每次只能向下或者向右移动一步。
 *
 * @author wuzheng.yk
 * @date 2024/3/22
 */
public class LC64minimum_path {

    public int minPathSum(int[][] grid) {
        final int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < m; i++) {
            dp[i][0] = grid[i][0] + dp[i - 1][0];
        }
        for (int i = 1; i < n; i++) {
            dp[0][i] = grid[0][i] + dp[0][i - 1];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[m - 1][n - 1];
    }

    public static void main(String[] args) {
        LC64minimum_path test = new LC64minimum_path();
        int[][] grid = { { 1, 2, 3 }, { 4, 5, 6 } };
        System.out.println(test.minPathSum(grid));
    }

}
