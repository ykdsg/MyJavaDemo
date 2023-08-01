package com.hz.yk.algo.camp.ch04_stack.practice1;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 * https://leetcode.cn/problems/valid-parentheses/
 * @author wuzheng.yk
 * @date 2023/7/18
 */
public class LC20valid_parentheses {
    static Map<Character,Character> cache=new HashMap<>();
    static {
        cache.put(')', '(');
        cache.put('}', '{');
        cache.put(']', '[');
    }
    
    public boolean isValid(String s) {
        if (s.length() % 2 != 0) {
            return false;
        }
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (cache.containsKey(c)) {
                if (stack.isEmpty() || stack.pop() != cache.get(c)) {
                    return false;
                } 
            }else {
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }
}
