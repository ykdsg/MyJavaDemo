package com.hz.yk.base.rule.common.api;

/**
 * @author wuzheng.yk
 * @date 2025/2/15
 */
public interface RuleEngine {

    /**
     * Fire rule on given facts.
     */
    void fire(Rule rule, Facts facts);

}
