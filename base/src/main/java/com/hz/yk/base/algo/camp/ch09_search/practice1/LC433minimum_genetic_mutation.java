package com.hz.yk.base.algo.camp.ch09_search.practice1;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 基因序列可以表示为一条由 8 个字符组成的字符串，其中每个字符都是 'A'、'C'、'G' 和 'T' 之一。
 * 假设我们需要调查从基因序列 start 变为 end 所发生的基因变化。一次基因变化就意味着这个基因序列中的一个字符发生了变化。
 * 另有一个基因库 bank 记录了所有有效的基因变化，只有基因库中的基因才是有效的基因序列。（变化后的基因必须位于基因库 bank 中）
 * 给你两个基因序列 start 和 end ，以及一个基因库 bank ，请你找出并返回能够使 start 变化为 end 所需的最少变化次数。如果无法完成此基因变化，返回 -1 。
 * https://leetcode.cn/problems/minimum-genetic-mutation/description/
 * @author wuzheng.yk
 * @date 2023/8/9
 */
public class LC433minimum_genetic_mutation {
    public int minMutation(String startGene, String endGene, String[] bank) {
        final Set<String> bankCache = Arrays.stream(bank).collect(Collectors.toSet());
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(new Node(startGene, 0));
        while (!queue.isEmpty()) {
            final Node node = queue.poll();
            if (Objects.equals(node.str, endGene)) {
                return node.count;
            }
            for (int i = 0; i < startGene.length(); i++) {
                final char[] charArray = node.str.toCharArray();
                for (char c : "ACGT".toCharArray()) {
                    if (charArray[i] == c) {
                        continue;
                    }
                    charArray[i] = c;
                    final String newStr = String.valueOf(charArray);
                    if (bankCache.contains(newStr)) {
                        queue.add(new Node(newStr, node.count + 1));
                        bankCache.remove(newStr);
                    }
                }
            }
        }
        return -1;
    }

    static class Node{

        String str;
        int count;

        public Node(String str, int count) {
            this.str = str;
            this.count = count;
        }
    }

    @Test
    public void test(){
        LC433minimum_genetic_mutation test = new LC433minimum_genetic_mutation();
        final int result = test.minMutation("AACCGGTT", "AACCGGTA", new String[]{ "AACCGGTA" });
        assertEquals(1, result);
    }
}
