package com.hz.yk.base.rule.common.core;

import com.hz.yk.base.rule.common.api.Facts;
import com.hz.yk.base.rule.common.api.Rule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;

/**
 * @author wuzheng.yk
 * @date 2025/2/15
 */
public class AllRules extends CompositeRule {

    private static final Logger LOGGER = LoggerFactory.getLogger(AllRules.class);

    public static CompositeRule allOf(Rule... rules) {
        CompositeRule instance = new AllRules();
        Collections.addAll(instance.rules, rules);
        return instance;
    }

    @Override
    public boolean evaluate(Facts facts) {
        if (rules.stream().allMatch(rule -> rule.evaluate(facts)))
            return true;
        return false;
    }

    @Override
    public void execute(Facts facts) {
        for (Rule rule : rules) {
            rule.execute(facts);
        }
    }

    @Override
    protected boolean doApply(Facts facts) {
        LOGGER.debug("start AND composite rule apply");
        if (evaluate(facts)) {
            for (Rule rule : rules) {
                //所有的rules都执行
                rule.execute(facts);
            }
            return true;
        }
        return false;
    }
}