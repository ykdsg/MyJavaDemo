package com.hz.yk.base.rule.common.api;

import java.util.Objects;

/**
 * @author wuzheng.yk
 * @date 2025/2/15
 */
@FunctionalInterface
public interface Condition {

    boolean evaluate(Facts facts);

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

    /**
     * A NoOp {@link Condition} that always returns false.
     */
    Condition FALSE = facts -> false;

    /**
     * A NoOp {@link Condition} that always returns true.
     */
    Condition TRUE = facts -> true;
}
