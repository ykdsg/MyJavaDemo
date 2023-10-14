package com.hz.yk.algo.camp.ch13_trie;

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
    Set<String> resultSet = new HashSet<>();

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

        //增加是否被使用过的标识
        //boolean hasUsed;

        void insert(String s) {
            TrieNode node = this;
            for (int i = 0; i < s.length(); i++) {
                int index = s.charAt(i) - 'a';
                if (node.tns[index] == null) {
                    node.tns[index] = new TrieNode();
                }
                node = node.tns[index];
            }
            node.str = s;
        }

        TrieNode containPrefix(String word) {
            TrieNode node = this;
            for (int i = 0; i < word.length(); i++) {
                final int index = word.charAt(i) - 'a';
                if (node.tns[index] == null) {
                    return null;
                }
                node = node.tns[index];
            }
            return node;
        }
    }

    public List<String> findWords2(char[][] board, String[] words) {
        this.board = board;
        m = board.length;
        n = board[0].length;
        vis = new boolean[m][n];

        Arrays.stream(words).forEach(word -> root.insert(word));
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs2(i, j, sb);
            }
        }
        return new ArrayList<>(resultSet);
    }

    private void dfs2(int i, int j, StringBuilder sb) {
        //递归退出条件
        if (i < 0 || i >= m || j < 0 || j >= n || vis[i][j]) {
            return;
        }
        sb.append(board[i][j]);

        final TrieNode trieNode = root.containPrefix(sb.toString());
        //通过trie 可以快速失败，降低无用迭代
        if (trieNode == null) {
            sb.deleteCharAt(sb.length() - 1);
            return;
        }
        //if (trieNode.str != null && !trieNode.hasUsed) {
        if (trieNode.str != null) {
            resultSet.add(sb.toString());
            //trieNode.hasUsed = true;
        }
        vis[i][j] = true;
        for (int[] d : dirs) {
            int dx = i + d[0], dy = j + d[1];
            dfs2(dx, dy, sb);
        }

        //    清理当前层数据
        vis[i][j] = false;
        sb.deleteCharAt(sb.length() - 1);
    }

    public List<String> findWords3(char[][] board, String[] words) {
        this.board = board;
        m = board.length;
        n = board[0].length;
        vis = new boolean[m][n];

        TrieNode trieNode = new TrieNode();
        Arrays.stream(words).forEach(trieNode::insert);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs3(i, j, trieNode);
            }
        }
        return new ArrayList<>(resultSet);
    }

    /**
     * triedNode 也作为每层的数据来处理，这样能直接把已经查到的单词从trieNode中删除
     *
     * @param i
     * @param j
     * @param trieNode
     */
    private void dfs3(int i, int j, TrieNode trieNode) {
        //递归退出条件
        final char ch = board[i][j];
        final TrieNode currentNode = trieNode.tns[ch - 'a'];
        if (currentNode == null) {
            return;
        }
        if (currentNode.str != null) {
            resultSet.add(currentNode.str);
            //这里可以剪枝，避免后续的重复判断
            // 这里不能直接剪枝，类似a,aa,aaa 这样的单词，如果遇到a 就直接干掉，后面的单词就没办法判断了。
            // 这里原先数组的结构就没办法很好的支撑这里的判断需求了，需要使用另外的数据结构
            trieNode.tns[ch - 'a'] = null;
        }
        vis[i][j] = true;
        for (int[] d : dirs) {
            int dx = i + d[0], dy = j + d[1];
            dfs3(dx, dy, currentNode);
        }

        //    清理当前层数据
        vis[i][j] = false;
    }

    static char[][] board1 = new char[][]{ { 'o', 'a', 'b', 'n' }, { 'o', 't', 'a', 'e' }, { 'a', 'h', 'k', 'r' },
                                           { 'a', 'f', 'l', 'v' } };

    static String[] words1 = new String[]{ "oa", "oaa" };

    static char[][] board2 = new char[][]{ { 'a' } };

    static String[] words2 = new String[]{ "a" };

    @Test
    public void test1() {
        LC212word_search2 test = new LC212word_search2();
        final List<String> wordsList1 = test.findWords(board1, words1);
        assertEquals(2, wordsList1.size());
    }

    @Test
    public void test2() {
        LC212word_search2 test = new LC212word_search2();
        final List<String> result1 = test.findWords2(board1, words1);
        assertEquals(2, result1.size());

        final List<String> result2 = test.findWords2(board2, words2);
        assertEquals(1, result2.size());
    }

    //这个case 通不过，因为上面的注释也说了剪枝会造成一些单词被干掉
    @Test
    public void test3() {
        LC212word_search2 test = new LC212word_search2();
        final List<String> result1 = test.findWords3(board1, words1);
        assertEquals(2, result1.size());

        final List<String> result2 = test.findWords3(board2, words2);
        assertEquals(1, result2.size());
    }
}
