package com.hz.yk.base.algo.hot100;

import java.util.HashMap;
import java.util.LinkedList;
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
 * @date 2024/1/26
 */
public class LC020valid_parentheses {

    static Map<Character, Character> cache = new HashMap<>();

    static {
        cache.put(')', '(');
        cache.put('}', '{');
        cache.put(']', '[');
    }

    public boolean isValid(String s) {
        LinkedList<Character> stack = new LinkedList<>();
        for (char c : s.toCharArray()) {
            //  遇到右括号就需要一定要有匹配的左括号
            if (cache.containsKey(c)) {
                if (stack.isEmpty()) {
                    return false;
                }
                if (cache.get(c) != stack.pop()) {
                    return false;
                }

            } else {
                //左括号的情况，直接入栈
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }
}
