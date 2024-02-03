package com.hz.yk.base.algo.hot100.practice2;

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
 * @date 2024/2/1
 */
public class LC020valid_parentheses {

    static Map<Character, Character> cahce = new HashMap<>();

    static {
        cahce.put(')', '(');
        cahce.put('}', '{');
        cahce.put(']', '[');
    }

    public boolean isValid(String s) {
        LinkedList<Character> stack = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            final char bracket = s.charAt(i);
            //右括号的情况
            if (cahce.containsKey(bracket)) {
                if (!stack.isEmpty() && stack.pop().equals(cahce.get(bracket))) {
                    continue;
                } else {
                    //没匹配的情况，直接抛错
                    return false;
                }

            } else {
                stack.push(bracket);
            }
        }
        return stack.isEmpty();
    }
}
