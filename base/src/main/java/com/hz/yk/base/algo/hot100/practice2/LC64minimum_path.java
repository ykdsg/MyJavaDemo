package com.hz.yk.base.algo.hot100.practice2;

/**
 * https://leetcode.cn/problems/minimum-path-sum/description/
 * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小
 * 每次只能向下或者向右移动一步。
 *
 * @author wuzheng.yk
 * @date 2024/2/23
 */
public class LC64minimum_path {

    public int minPathSum(int[][] grid) {
        final int row = grid.length;
        final int col = grid[0].length;
        for (int i = 1; i < row; i++) {
            grid[i][0] = grid[i - 1][0] + grid[i][0];
        }
        for (int i = 1; i < col; i++) {
            grid[0][i] = grid[0][i - 1] + grid[0][i];
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                grid[i][j] = Math.min(grid[i - 1][j], grid[i][j - 1]) + grid[i][j];
            }
        }
        return grid[row - 1][col - 1];
    }

}
