package com.hz.yk.base.algo.hot100.practice1;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.cn/problems/valid-parentheses/description/
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 * 有效字符串需满足：
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 每个右括号都有一个对应的相同类型的左括号。
 *
 * @author wuzheng.yk
 * @date 2024/3/22
 */
public class LC020valid_parentheses {

    private Map<Character, Character> cache = new HashMap<>();

    {
        cache.put(')', '(');
        cache.put(']', '[');
        cache.put('}', '{');
    }

    public boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            final char c = s.charAt(i);
            if (cache.containsKey(c)) {
                if (stack.isEmpty() || stack.pop() != cache.get(c)) {
                    return false;
                }
            } else {
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }

}
