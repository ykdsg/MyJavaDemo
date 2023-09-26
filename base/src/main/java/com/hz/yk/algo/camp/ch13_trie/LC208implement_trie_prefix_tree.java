package com.hz.yk.algo.camp.ch13_trie;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author wuzheng.yk
 * @date 2023/9/26
 */
public class LC208implement_trie_prefix_tree {

    static class Trie {

        private Trie[] children;
        private boolean isEnd;

        public Trie() {
            children = new Trie[26];
            isEnd = false;

        }

        public void insert(String word) {
            Trie node = this;
            for (int i = 0; i < word.length(); i++) {
                final char ch = word.charAt(i);
                final int index = ch - 'a';
                if (node.children[index] == null) {
                    node.children[index] = new Trie();
                }
                node = node.children[index];
            }
            node.isEnd = true;
        }

        public boolean search(String word) {
            final Trie trie = searchPrefix(word);
            return trie != null && trie.isEnd;
        }

        public boolean startsWith(String prefix) {
            final Trie node = searchPrefix(prefix);
            return node != null;

        }

        private Trie searchPrefix(String word) {
            Trie node = this;
            for (int i = 0; i < word.length(); i++) {
                final char ch = word.charAt(i);
                final int index = ch - 'a';
                if (node.children[index] == null) {
                    return null;
                }
                node = node.children[index];
            }

            return node;
        }
    }

    @Test
    public void test() {
        Trie trie = new Trie();
        trie.insert("apple");
        final boolean result = trie.search("apple");

        Assertions.assertTrue(result);
    }
}
