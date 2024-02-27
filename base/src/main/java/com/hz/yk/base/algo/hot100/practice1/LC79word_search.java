package com.hz.yk.base.algo.hot100.practice1;

/**
 * https://leetcode.cn/problems/word-search/description/
 * 给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 *
 * @author wuzheng.yk
 * @date 2024/2/26
 */
public class LC79word_search {

    int[][] steps = { { 0, -1 }, { -1, 0 }, { 0, 1 }, { 1, 0 } };

    public boolean exist(char[][] board, String word) {
        final int row = board.length;
        final int col = board[0].length;

        boolean[][] readed = new boolean[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (dfs(board, word, i, j, readed, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, String word, int i, int j, boolean[][] readed, int start) {
        if (start == word.length()) {
            return true;
        }
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || readed[i][j]) {
            return false;
        }
        if (board[i][j] != word.charAt(start)) {
            return false;
        }
        readed[i][j] = true;
        for (int[] step : steps) {
            if (dfs(board, word, i + step[0], j + step[1], readed, start + 1)) {
                return true;
            }
        }
        readed[i][j] = false;
        return false;
    }
}
