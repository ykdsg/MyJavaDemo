package com.hz.yk.base.rule.common.api;

/**
 * @author wuzheng.yk
 * @date 2025/2/15
 */
@FunctionalInterface
public interface Action {

    void execute(Facts facts);

}
