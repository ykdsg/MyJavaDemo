package com.hz.yk.base.algo.hot100.practice3;

/**
 * https://leetcode.cn/problems/word-search/description/
 * 给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 *
 * @author wuzheng.yk
 * @date 2024/5/14
 */
public class LC79word_search {

    public boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (check(board, i, j, word, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean check(char[][] board, int i, int j, String word, int path) {
        if (path == word.length()) {
            return true;
        }
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != word.charAt(path)) {
            return false;
        }
        board[i][j] = '#';
        boolean result = check(board, i + 1, j, word, path + 1) || check(board, i - 1, j, word, path + 1) ||
                         check(board, i, j + 1, word, path + 1) || check(board, i, j - 1, word, path + 1);
        board[i][j] = word.charAt(path);
        return result;
    }

}
