package com.hz.yk.base.rule.common.core;

import com.hz.yk.base.rule.common.api.Facts;
import com.hz.yk.base.rule.common.api.Rule;
import com.hz.yk.base.rule.common.api.RuleEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author wuzheng.yk
 * @date 2025/2/15
 */
public class DefaultRuleEngine implements RuleEngine {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultRuleEngine.class);

    @Override
    public void fire(Rule rule, Facts facts) {
        if (rule == null) {
            LOGGER.error("Rules is null! Nothing to apply");
            return;
        }
        rule.apply(facts);
    }
}
