package com.hz.yk.algo.camp.ch13_trie;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author wuzheng.yk
 * @date 2023/10/14
 */
public class LC212word_search2_new {

    int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

    char[][] board;

    public List<String> findWords(char[][] board, String[] words) {
        this.board = board;
        Trie root = new Trie();
        Arrays.stream(words).forEach(root::insert);
        //因为决定对trie进行剪枝，所以不会存在重复的单词，这里直接用list而不用set
        List<String> result = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(i, j, root, result);
            }
        }
        return result;
    }

    private void dfs(int i, int j, Trie trie, List<String> result) {
        //递归终止条件
        if (trie.children.isEmpty()) {
            return;
        }
        char ch = board[i][j];
        Trie currentTrie = trie.children.get(ch);
        //如果没有，可以尽早返回，不用做无用功
        if (currentTrie == null) {
            return;
        }

        final boolean isWord = !Objects.equals("", currentTrie.str);
        if (isWord) {
            result.add(currentTrie.str);
            currentTrie.str = "";
            //已经匹配过的情况下，如果不是中间路径节点可以进行剪枝
            //这里的剪枝对性能提升非常明显i
            if (currentTrie.children.isEmpty()) {
                trie.children.remove(ch);
            }
        }

        //如果存在就继续下探看下有没有可能存在
        board[i][j] = '#';
        for (int[] dir : dirs) {
            int dx = i + dir[0], dy = j + dir[1];
            if (dx < 0 || dx >= board.length || dy < 0 || dy >= board[0].length) {
                continue;
            }
            dfs(dx, dy, currentTrie, result);
        }
        //清理当前层
        board[i][j] = ch;
    }

    static class Trie {

        String str;

        Map<Character, Trie> children;

        public Trie() {
            str = "";
            children = new HashMap<>();
        }

        public void insert(String word) {
            Trie node = this;
            for (char c : word.toCharArray()) {
                node = node.children.computeIfAbsent(c, character -> new Trie());
            }
            node.str = word;
        }
    }

    static char[][] board1 = new char[][]{ { 'o', 'a', 'b', 'n' }, { 'o', 't', 'a', 'e' }, { 'a', 'h', 'k', 'r' },
                                           { 'a', 'f', 'l', 'v' } };

    static String[] words1 = new String[]{ "oa", "oaa" };

    static char[][] board2 = new char[][]{ { 'a' } };

    static String[] words2 = new String[]{ "a" };

    @Test
    public void test1() {
        LC212word_search2_new test = new LC212word_search2_new();
        final List<String> wordsList1 = test.findWords(board1, words1);
        assertEquals(2, wordsList1.size());

        final List<String> result2 = test.findWords(board2, words2);
        assertEquals(1, result2.size());
    }
}
