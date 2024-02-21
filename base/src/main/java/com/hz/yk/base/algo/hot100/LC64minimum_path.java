package com.hz.yk.base.algo.hot100;

/**
 * https://leetcode.cn/problems/minimum-path-sum/description/
 * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小
 * 每次只能向下或者向右移动一步。
 *
 * @author wuzheng.yk
 * @date 2024/2/19
 */
public class LC64minimum_path {

    /**
     * 注意对0行0列的初始化
     * dp[i][j]=min(dp[i-1][j],dp[i][j-1])+dp[i][j]
     *
     * @param grid
     * @return
     */
    public int minPathSum(int[][] grid) {
        //    这里直接用grid 作为动态规划数组，少了内存开销
        final int row = grid.length;
        final int col = grid[0].length;
        for (int i = 1; i < row; i++) {
            grid[i][0] += grid[i - 1][0];
        }
        for (int j = 1; j < col; j++) {
            grid[0][j] += grid[0][j - 1];
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                grid[i][j] += Math.min(grid[i - 1][j], grid[i][j - 1]);
            }
        }
        return grid[row - 1][col - 1];
    }

    public static void main(String[] args) {
        LC64minimum_path test = new LC64minimum_path();
        int[] row1 = new int[]{ 1, 3, 1 };
        int[] row2 = new int[]{ 1, 5, 1 };
        int[] row3 = new int[]{ 4, 2, 1 };
        int[][] grid = new int[][]{ row1, row2, row3 };
        final int result = test.minPathSum(grid);
        System.out.println(result);

    }
}
