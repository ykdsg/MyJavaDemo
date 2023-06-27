package com.hz.yk.algo.camp.ch05_hash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wuzheng.yk
 * @date 2023/6/27
 */
public class LC49group_anagrams {

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String,List<String>> resultMap = new HashMap<>();
        for (String str : strs) {
            int[] charCount = new int[26];
            for (int i = 0; i < str.length(); i++) {
                charCount[str.charAt(i) - 'a']++;
            }
            //这里需要转成string，因为int[]的hashcode是根据内存地址计算的，而不是数组的值
            final String key = Arrays.toString(charCount);
            resultMap.computeIfAbsent(key, k -> new ArrayList<>()).add(str);
        }
        return new ArrayList<>(resultMap.values());
    }

    public static void main(String[] args) {
        LC49group_anagrams test = new LC49group_anagrams();
        System.out.println(test.groupAnagrams(new String[] {"eat", "tea", "tan", "ate", "nat", "bat"}));
    }
    
}
