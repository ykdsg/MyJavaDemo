package com.hz.yk.base.algo.camp.ch09_search;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 基因序列可以表示为一条由 8 个字符组成的字符串，其中每个字符都是 'A'、'C'、'G' 和 'T' 之一。
 * 假设我们需要调查从基因序列 start 变为 end 所发生的基因变化。一次基因变化就意味着这个基因序列中的一个字符发生了变化。
 * @author wuzheng.yk
 * @date 2023/7/26
 */
public class LC433minimum_genetic_mutation {

    public int minMutation(String startGene, String endGene, String[] bank) {
        Queue<Node> queue = new ArrayDeque<>();
        final Set<String> bankSet = Arrays.stream(bank).collect(Collectors.toSet());
        queue.add(new Node(startGene, 0));
        while (!queue.isEmpty()) {
            final Node node = queue.poll();
            final String strartStr = node.str;
            if (Objects.equals(strartStr, endGene)) {
                return node.num;
            }
            for (int i = 0; i < strartStr.length(); i++) {
                //这里还不能这么剪枝，因为最关键的不是 strartStr 跟 endGene 之间是否相等，而是strartStr 变换之后是否满足 bankSet。
                // 这里可能的逻辑是A -> A`...->B  ，这个中间过程可能很多，所以这里要看A 是否每次变化都能落在 bankSet 里面
                //if (strartStr.charAt(i) == endGene.charAt(i)) {
                //    continue;
                //}
                for (char c : "ACGT".toCharArray()) {
                    final char[] strChars = strartStr.toCharArray();
                    if (strChars[i] == c) {
                        continue;
                    }
                    strChars[i] = c;
                    final String newStr = String.valueOf(strChars);
                    if (!Objects.equals(newStr, strartStr) && bankSet.contains(newStr)) {
                        queue.offer(new Node(newStr, node.num + 1));
                        bankSet.remove(newStr);
                    }
                }
            }
        }
        return -1;
    }

    static class Node {

        String str;
        int num;

        public Node(String str, int num) {
            this.str = str;
            this.num = num;
        }
    }

        public static void main(String[] args) {
            LC433minimum_genetic_mutation test = new LC433minimum_genetic_mutation();
            final int result = test.minMutation("AACCGGTT", "AAACGGTA",
                                                new String[]{ "AACCGATT","AACCGATA","AAACGATA","AAACGGTA" });
            System.out.println(result);

        }
    }
