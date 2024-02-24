package com.hz.yk.base.algo.hot100.practice2;

/**
 * https://leetcode.cn/problems/rotate-image/solutions/526980/xuan-zhuan-tu-xiang-by-leetcode-solution-vu3m/
 * 给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
 * 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
 *
 * @author wuzheng.yk
 * @date 2024/2/22
 */
public class LC48rotate_image {

    /**
     * 可以理解为先上下翻转，再沿对角线翻转
     *
     * @param matrix
     */
    public void rotate(int[][] matrix) {
        final int row = matrix.length;
        final int col = matrix[0].length;
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

    private void swap(int[][] matrix, int row1, int col1, int row2, int col2) {
        final int tmp = matrix[row1][col1];
        matrix[row1][col1] = matrix[row2][col2];
        matrix[row2][col2] = tmp;
    }

}
