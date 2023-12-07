package com.hz.yk.base.algo.camp.ch09_search;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;

/**
 * @author wuzheng.yk
 * @date 2023/7/27
 */
public class LC127word_ladder {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (beginWord.length() != endWord.length()) {
            return 0;
        }
        final Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)) {
            return 0;
        }
        Queue<Node> queue = new ArrayDeque<>();
        //这里计算的是序列长度，所以初始值为1
        queue.offer(new Node(beginWord, 1));
        while (!queue.isEmpty()) {
            final Node node = queue.poll();
            if (Objects.equals(node.str, endWord)) {
                return node.num;
            }
            final String str = node.str;
            for (int i = 0; i < str.length(); i++) {
                final StringBuilder sb = new StringBuilder(str);
                for (int j = 0; j < 26; j++) {
                    sb.setCharAt(i, (char) ('a' + j));
                    final String newStr = sb.toString();
                    if (!Objects.equals(newStr, str) && wordSet.contains(newStr)) {
                        queue.offer(new Node(newStr, node.num + 1));
                        wordSet.remove(newStr);
                    }
                }
            }
        }
        return 0;
    }

    static class Node{

        String str;
        int num;

        public Node(String str, int num) {
            this.str = str;
            this.num = num;
        }
    }

    public static void main(String[] args) {
        LC127word_ladder test = new LC127word_ladder();
        String[] words = new String[]{ "hot", "dot", "dog", "lot", "log", "cog" };
        final int result = test.ladderLength("hit", "cog", Arrays.asList(words));
        System.out.println(result);

    }
}
