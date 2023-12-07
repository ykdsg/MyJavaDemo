package com.hz.yk.base.algo.camp.ch05_hash.practice1;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 * 注意：若 s 和 t 中每个字符出现的次数都相同，则称 s 和 t 互为字母异位词。
 * @author wuzheng.yk
 * @date 2023/7/20
 */
public class LC242valid_anagram {

    public boolean isAnagram1(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        Map<Character, Integer> cache = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            final char skey = s.charAt(i);
            cache.put(skey, cache.getOrDefault(skey, 0)+1);
            final char tkey = t.charAt(i);
            cache.put(tkey, cache.getOrDefault(tkey, 0) - 1);
        }
        for (Map.Entry<Character, Integer> entry : cache.entrySet()) {
            if (entry.getValue() != 0) {
                return false;
            }
        }
        return true;
    }

    // 使用数组实现，比hashmap 更快
    public boolean isAnagram2(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] cache = new int[26];
        for (int i = 0; i < s.length(); i++) {
            cache[s.charAt(i) - 'a'] += 1;
            cache[t.charAt(i) - 'a'] -= 1;
        }
        for (int v : cache) {
            if (v != 0) {
                return false;
            }
        }
        return true;
    }

}
