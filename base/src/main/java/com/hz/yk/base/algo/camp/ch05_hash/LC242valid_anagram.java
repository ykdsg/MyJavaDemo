package com.hz.yk.base.algo.camp.ch05_hash;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wuzheng.yk
 * @date 2023/6/27
 */
public class LC242valid_anagram {

    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        Map<String, Integer> charMap = new HashMap();
        for (int i = 0; i < s.length(); i++) {
            charMap.put(String.valueOf(s.charAt(i)), charMap.getOrDefault(String.valueOf(s.charAt(i)), 0) + 1);
            charMap.put(String.valueOf(t.charAt(i)), charMap.getOrDefault(String.valueOf(t.charAt(i)), 0) - 1);
        }

        for (Map.Entry<String, Integer> entry : charMap.entrySet()) {
            if (entry.getValue() != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 利用数组的变体，来实现hashmap，内存使用更高效
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram2(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] records = new int[26];
        for (int i = 0; i < s.length(); i++) {
            records[s.charAt(i) - 'a']++;
            records[t.charAt(i) - 'a']--;
        }
        for (int aChar : records) {
            if (aChar != 0) {
                return false;
            }
        }
        return true;
    }

}
