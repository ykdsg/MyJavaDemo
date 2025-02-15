package com.hz.yk.base.rule.fizzbuzz.engin;

import java.util.Objects;

/**
 * @author wuzheng.yk
 * @date 2025/1/24
 */
@FunctionalInterface
public interface Condition {

    boolean evaluate(int n);

    default Condition and(Condition other) {
        Objects.requireNonNull(other);
        return (n) -> {
            return evaluate(n) && other.evaluate(n);
        };
    }

    default Condition or(Condition other) {
        Objects.requireNonNull(other);
        return (n) -> {
            return evaluate(n) || other.evaluate(n);
        };
    }
}
