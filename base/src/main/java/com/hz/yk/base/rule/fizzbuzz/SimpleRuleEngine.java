package com.hz.yk.base.rule.fizzbuzz;

import com.hz.yk.base.rule.fizzbuzz.engin.Action;
import com.hz.yk.base.rule.fizzbuzz.engin.Condition;
import com.hz.yk.base.rule.fizzbuzz.engin.Rule;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 通过原子atom rule，以及atom rule之间的组合解决FizzBuzz问题
 * 这里为了简单使用Rule的组合模式代替了RuleEngine实体
 * 注意：这个SimpleRuleEngine只能解决输入为n，输出为String的FizzBuzz问题
 * 完全不具备通用性
 *
 * @author wuzheng.yk
 * @date 2025/1/24
 */
public class SimpleRuleEngine {

    public static Rule atom(Condition condition, Action action) {
        return n -> {
            return condition.evaluate(n) ? action.execute(n) : "";
        };
    }

    public static Rule anyOf(Rule... rules) {
        return n -> {
            return stringStream(n, rules).filter(t -> !t.isEmpty()).findFirst().orElse("");
        };
    }

    public static Rule allOf(Rule... rules) {
        return n -> {
            return stringStream(n, rules).collect(Collectors.joining());
        };
    }

    private static Stream<String> stringStream(int n, Rule[] rules) {
        return Arrays.stream(rules).map(rule -> rule.apply(n));
    }
}
