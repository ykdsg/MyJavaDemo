package com.hz.yk.algo.camp.ch13_trie;

import org.jetbrains.annotations.Nullable;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 给定一个 m x n 二维字符网格 board 和一个单词（字符串）列表 words， 返回所有二维网格上的单词 。
 * 单词必须按照字母顺序，通过 相邻的单元格 内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。
 * 同一个单元格内的字母在一个单词中不允许被重复使用。
 *
 * @author wuzheng.yk
 * @date 2023/10/10
 */
public class LC212word_search2 {

    Set<String> wordSet = new HashSet<>();
    char[][] board;
    int[][] dirs = new int[][]{ { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
    int n, m;
    /**
     * 用于记录是否访问过某一格，避免走回头路导致重复使用
     */
    boolean[][] vis;
    List<String> resultWrods = new ArrayList<>();

    public List<String> findWords(char[][] board, String[] words) {
        this.board = board;
        m = board.length;
        n = board[0].length;
        vis = new boolean[m][n];
        wordSet.addAll(Arrays.asList(words));
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(i, j, sb);
            }
        }
        return resultWrods;
    }

    private void dfs(int i, int j, StringBuilder sb) {
        //递归退出条件，这里其实效率有点差，不能较快判断出单词前缀是否存在
        if (sb.length() > 10) {
            return;
        }
        sb.append(board[i][j]);
        vis[i][j] = true;

        if (wordSet.contains(sb.toString())) {
            resultWrods.add(sb.toString());
            wordSet.remove(sb.toString());
        }
        for (int[] d : dirs) {
            int dx = i + d[0], dy = j + d[1];
            if (dx < 0 || dx >= m || dy >= n || dy < 0 || vis[dx][dy]) {
                continue;
            }
            dfs(dx, dy, sb);
        }
        vis[i][j] = false;
        sb.deleteCharAt(sb.length() - 1);
    }

    /*
     *---------------------使用Trie 树提升效率-----------------------------
     */

    TrieNode root = new TrieNode();

    class TrieNode {

        //将常规的 isEnd 标记属性直接换成记录当前字符 s，这样我们在 DFS 的过程中则无须额外记录当前搜索字符串。
        String str;
        TrieNode[] tns = new TrieNode[26];
    }

    void insert(String s) {
        TrieNode node = root;
        for (int i = 0; i < s.length(); i++) {
            int index = s.charAt(i) - 'a';
            if (node.tns[index] == null) {
                node.tns[index] = new TrieNode();
            }
            node = node.tns[index];
        }
        node.str = s;
    }

    @Nullable TrieNode searchPrefix(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            final int index = word.charAt(i) - 'a';
            if (node.tns[index] == null) {
                return null;
            }
            node = node.tns[index];
        }
        return node;
    }

    public List<String> findWords2(char[][] board, String[] words) {
        this.board = board;
        m = board.length;
        n = board[0].length;
        vis = new boolean[m][n];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs2(i, j, sb);
            }
        }
        return resultWrods;
    }

    private void dfs2(int i, int j, StringBuilder sb) {
        final TrieNode trieNode = searchPrefix(sb.toString());
        //通过trie 可以快速失败，降低无用迭代
        if (trieNode == null) {
            return;
        }
        if (trieNode.str != null) {
            resultWrods.add(sb.toString());
            wordSet.remove(sb.toString());
        }
        sb.append(board[i][j]);
        vis[i][j] = true;
        for (int[] d : dirs) {
            int dx = i + d[0], dy = j + d[1];
            if (dx < 0 || dx >= m || dy < 0 || dy >= n || vis[dx][dy]) {
                continue;
            }
            dfs2(dx, dy, sb);
        }

        //    清理当前层数据
        vis[i][j] = false;
        sb.deleteCharAt(sb.length() - 1);
    }

    @Test
    public void test() {
        char[][] board = new char[][]{ { 'o', 'a', 'b', 'n' }, { 'o', 't', 'a', 'e' }, { 'a', 'h', 'k', 'r' },
                                       { 'a', 'f', 'l', 'v' } };

        String[] words = new String[]{ "oa", "oaa" };
        LC212word_search2 test = new LC212word_search2();
        final List<String> words1 = test.findWords(board, words);
        assertEquals(2, words1.size());

        final List<String> words2 = test.findWords2(board, words);
        assertEquals(2, words2.size());
    }
}
