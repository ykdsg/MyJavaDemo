package com.hz.yk.base.rule.fizzbuzz;

import com.hz.yk.base.rule.fizzbuzz.engin.Condition;

/**
 * @author wuzheng.yk
 * @date 2025/1/24
 */
public class TimesCondition {

    public static Condition times(int i) {
        return n -> n % i == 0;
    }

}
