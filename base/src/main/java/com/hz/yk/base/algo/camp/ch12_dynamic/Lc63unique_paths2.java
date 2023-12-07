package com.hz.yk.base.algo.camp.ch12_dynamic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish”）。
 * 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
 * 网格中的障碍物和空位置分别用 1 和 0 来表示。
 *
 * @author wuzheng.yk
 * @date 2023/10/18
 */
public class Lc63unique_paths2 {

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int row = obstacleGrid.length;
        int column = obstacleGrid[0].length;
        int[][] dp = new int[row][column];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                } else {
                    if (i == 0 && j == 0) {
                        dp[i][j] = 1;
                    } else if (i == 0) {
                        dp[i][j] = dp[i][j - 1];
                    } else if (j == 0) {
                        dp[i][j] = dp[i - 1][j];
                    } else {
                        dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                    }
                }

            }
        }
        return dp[row - 1][column - 1];
    }

    /**
     * 进行初始化的操作
     *
     * @param obstacleGrid
     * @return
     */

    public int uniquePathsWithObstacles2(int[][] obstacleGrid) {
        int row = obstacleGrid.length;
        int column = obstacleGrid[0].length;
        int[][] dp = new int[row][column];

        dp[0][0] = obstacleGrid[0][0] == 1 ? 0 : 1;
        for (int i = 1; i < row; i++) {
            dp[i][0] = obstacleGrid[i][0] == 1 ? 0 : dp[i - 1][0];
        }
        for (int j = 1; j < column; j++) {
            dp[0][j] = obstacleGrid[0][j] == 1 ? 0 : dp[0][j - 1];
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < column; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[row - 1][column - 1];
    }

    @Test
    public void test() {
        Lc63unique_paths2 test = new Lc63unique_paths2();
        int[][] obstacleGrid = { { 0, 0, 0 }, { 0, 1, 0 }, { 0, 0, 0 } };
        int result2 = test.uniquePathsWithObstacles(obstacleGrid);
        assertEquals(2, result2);
        result2 = test.uniquePathsWithObstacles2(obstacleGrid);
        assertEquals(2, result2);

    }

    @Test
    public void test2() {
        Lc63unique_paths2 test = new Lc63unique_paths2();
        int[][] obstacleGrid = { { 1, 0 } };
        int result0 = test.uniquePathsWithObstacles(obstacleGrid);
        assertEquals(0, result0);
        result0 = test.uniquePathsWithObstacles2(obstacleGrid);
        assertEquals(0, result0);

    }
}

