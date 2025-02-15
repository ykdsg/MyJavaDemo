package com.hz.yk.base.rule.fizzbuzz;

import com.hz.yk.base.rule.fizzbuzz.engin.Rule;

import static com.hz.yk.base.rule.fizzbuzz.SimpleRuleEngine.allOf;
import static com.hz.yk.base.rule.fizzbuzz.SimpleRuleEngine.anyOf;
import static com.hz.yk.base.rule.fizzbuzz.SimpleRuleEngine.atom;
import static com.hz.yk.base.rule.fizzbuzz.TimesCondition.times;

/**
 * @author wuzheng.yk
 * @date 2025/1/24
 */
public class FizzBuzz {

    public static String count(int i) {
        // 定义规则
        //Composite condition
        Rule fizzBuzzRule = atom(times(3).and(times(5)), n -> "FizzBuzz");
        Rule fizzRule = atom(times(3), n -> "Fizz");
        Rule buzzRule = atom(times(5), n -> "Buzz");

        //Composite rule
        //    fizzBuzzRule 在这里可以使用组合规则，因为刚好字符串拼接FizzBuzz 刚好是2个规则的组合，如果不是这里就不能用组合规则
        final Rule compositeRule = allOf(fizzBuzzRule, fizzRule, buzzRule);
        final Rule defaultRule = atom(n -> true, n -> String.valueOf(n));

        final Rule rule = anyOf(fizzBuzzRule, fizzRule, buzzRule, defaultRule);
        return rule.apply(i);
    }
}
