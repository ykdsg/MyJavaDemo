package com.hz.yk.algo.camp.ch09_search;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * @author wuzheng.yk
 * @date 2023/7/27
 */
public class LC126word_ladder_2 {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> result = new ArrayList<>();
        if (beginWord.length() != endWord.length()) {
            return result;
        }
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)) {
            return result;
        }
        Queue<Node> queue = new ArrayDeque<>();
        final ArrayList<String> path = new ArrayList<>();
        path.add(beginWord);
        queue.offer(new Node(beginWord));
        
        while (!queue.isEmpty()) {
            final int layerNum = queue.size();
            Set<String> layerUsedCache = new HashSet<>();
            
            //说明是在当前层查找
            for (int k = 0; k < layerNum; k++) {
                final Node node = queue.poll();
                if (node.str.equals(endWord)) {
                    result.add(node.path);
                    continue;
                }
                final String str = node.str;
                final char[] charArray = node.str.toCharArray();
                
                for (int i = 0; i < str.length(); i++) {
                    char origin = charArray[i];
                    for (char c = 'a'; c <= 'z'; c++) {
                        charArray[i] = c;
                        final String newStr = String.valueOf(charArray);
                        //这里虽然剪枝了，但是会存在效率问题，因为相同字符串会进入queue，导致计算的重复
                        //如果要解决这个问题，就不能使用这个结构体，而是要维护一个map，key 是字符串，value 是从变化上一步的字符串List
                        //这样的话最后的链条就需要回溯拼装回来
                        if (wordSet.contains(newStr) || layerUsedCache.contains(newStr)) {
                            layerUsedCache.add(newStr);
                            wordSet.remove(newStr);
                            queue.offer(node.create(newStr));
                        }
                    }
                    charArray[i] = origin;
                }
            }
            // 如果当前层迭代完能够找到，就说明是最短路径。因为再往下一层路径越长
            if (!result.isEmpty()) {
                return result;
            }
        }
        return result;
    }
    static class Node{
        String str;
        List<String> path;
                
        public Node(String str) {
            this.str = str;
            this.path = new ArrayList<>();
            this.path.add(str);
        }

        public Node create(String str) {
            Node node = new Node(str);
            final ArrayList<String> nPath = new ArrayList<>(path);
            nPath.add(str);
            node.path = nPath;
            return node;
        }
    }


    public static void main(String[] args) {
        LC126word_ladder_2 test = new LC126word_ladder_2();
        String[] words = new String[]{ "hot", "dot", "dog", "lot", "log", "cog" };
        final List<List<String>> result = test.findLadders("hit", "cog", Arrays.asList(words));
        System.out.println(result);

    }
}
