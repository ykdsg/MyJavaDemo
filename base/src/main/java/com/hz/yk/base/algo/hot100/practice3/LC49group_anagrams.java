package com.hz.yk.base.algo.hot100.practice3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.cn/problems/group-anagrams/
 * 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
 * 字母异位词 是由重新排列源单词的所有字母得到的一个新单词。
 *
 * @author wuzheng.yk
 * @date 2024/3/1
 */
public class LC49group_anagrams {

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> cache = new HashMap<>();
        for (String str : strs) {
            final char[] chars = str.toCharArray();
            Arrays.sort(chars);
            cache.computeIfAbsent(new String(chars), k -> new ArrayList<>()).add(str);
        }
        return new ArrayList<>(cache.values());
    }
}
