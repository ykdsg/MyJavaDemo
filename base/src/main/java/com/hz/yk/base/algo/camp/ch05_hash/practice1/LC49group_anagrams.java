package com.hz.yk.base.algo.camp.ch05_hash.practice1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
 * 字母异位词 是由重新排列源单词的所有字母得到的一个新单词。
 * https://leetcode.cn/problems/group-anagrams/
 * @author wuzheng.yk
 * @date 2023/7/20
 */
public class LC49group_anagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> cache = new HashMap<>();
        for (String str : strs) {
            int[] codes = new int[26];
            for (char c : str.toCharArray()) {
                codes[c - 'a'] += 1;
            }
            cache.computeIfAbsent(Arrays.toString(codes), v -> new ArrayList<>()).add(str);
        }
        return new ArrayList<>(cache.values());
    }

    public static void main(String[] args) {

    }
}
