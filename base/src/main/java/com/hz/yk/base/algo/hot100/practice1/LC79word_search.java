package com.hz.yk.base.algo.hot100.practice1;

/**
 * https://leetcode.cn/problems/word-search/description/
 * 给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 *
 * @author wuzheng.yk
 * @date 2024/3/10
 */
public class LC79word_search {

    public boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (dfs(board, i, j, word, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, int i, int j, String word, int index) {
        // 注意这个要放最前面判断，否则可能先返回false，这个就不对了
        if (index == word.length()) {
            return true;
        }
        if (i < 0 || j < 0 || i == board.length || j == board[0].length) {
            return false;
        }
        if (word.charAt(index) != board[i][j]) {
            return false;
        }
        board[i][j] = '#';
        if (dfs(board, i + 1, j, word, index + 1) || dfs(board, i, j + 1, word, index + 1) ||
            dfs(board, i - 1, j, word, index + 1) || dfs(board, i, j - 1, word, index + 1)) {
            return true;
        }
        board[i][j] = word.charAt(index);
        return false;

    }

}
