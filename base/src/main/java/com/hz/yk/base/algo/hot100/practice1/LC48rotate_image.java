package com.hz.yk.base.algo.hot100.practice1;

/**
 * https://leetcode.cn/problems/rotate-image/description/
 * 给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
 * 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
 *
 * @author wuzheng.yk
 * @date 2024/3/7
 */
public class LC48rotate_image {

    /**
     * 旋转90度可以转换成上下翻转，再沿对角线翻转
     * 12
     * 34
     *
     * @param matrix
     */
    public void rotate(int[][] matrix) {
        final int row = matrix.length, col = matrix[0].length;
        for (int i = 0; i < row / 2; i++) {
            for (int j = 0; j < col; j++) {
                swap(matrix, i, j, row - i - 1, j);
            }
        }
        for (int i = 0; i < row; i++) {
            for (int j = i + 1; j < col; j++) {
                swap(matrix, i, j, j, i);
            }
        }
    }

    private void swap(int[][] matrix, int i, int j, int i1, int j1) {
        int tmp = matrix[i][j];
        matrix[i][j] = matrix[i1][j1];
        matrix[i1][j1] = tmp;
    }

}
