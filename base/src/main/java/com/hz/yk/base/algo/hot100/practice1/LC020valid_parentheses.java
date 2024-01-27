package com.hz.yk.base.algo.hot100.practice1;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;

/**
 * https://leetcode.cn/problems/valid-parentheses/description/
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 * 有效字符串需满足：
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 每个右括号都有一个对应的相同类型的左括号。
 *
 * @author wuzheng.yk
 * @date 2024/1/27
 */
public class LC020valid_parentheses {

    static Map<Character, Character> cache = new HashMap<>();

    static {
        cache.put(')', '(');
        cache.put(']', '[');
        cache.put('}', '{');
    }

    public boolean isValid(String s) {
        LinkedList<Character> stack = new LinkedList<>();
        for (char c : s.toCharArray()) {
            // 遇到右括号
            if (cache.containsKey(c)) {
                // 看看有没有匹配的左括号
                if (stack.isEmpty() || !Objects.equals(cache.get(c), stack.pop())) {
                    return false;
                }
            } else {
                // 遇到左括号，直接进栈
                stack.push(c);
            }
        }

        //最后检查下栈是否为空，如果完全匹配栈就应该是空的
        return stack.isEmpty();
    }
}
