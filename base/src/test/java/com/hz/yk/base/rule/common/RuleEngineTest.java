package com.hz.yk.base.rule.common;

import com.hz.yk.base.rule.common.api.Facts;
import com.hz.yk.base.rule.common.api.Rule;
import com.hz.yk.base.rule.common.api.RuleEngine;
import com.hz.yk.base.rule.common.core.AllRules;
import com.hz.yk.base.rule.common.core.AnyRules;
import com.hz.yk.base.rule.common.core.DefaultRule;
import com.hz.yk.base.rule.common.core.DefaultRuleEngine;
import org.junit.jupiter.api.Test;

/**
 * @author wuzheng.yk
 * @date 2025/2/15
 */
public class RuleEngineTest {

    @Test
    public void testRuleEngine() {
        RuleEngine ruleEngine = new DefaultRuleEngine();
        ruleEngine.fire(null, null);
    }

    @Test
    public void testFizzBuzz() {
        RuleEngine fizzBuzzEngine = new DefaultRuleEngine();

        // create rules
        Rule fizzRule = new DefaultRule(facts -> (int) facts.get("number") % 3 == 0, facts -> System.out.print("Fizz"));
        Rule buzzRule = new DefaultRule(facts -> (int) facts.get("number") % 5 == 0, facts -> System.out.print("Buzz"));
        Rule fizzBuzzRule = AllRules.allOf(fizzRule, buzzRule);
        Rule NonFizzBuzzRule = new DefaultRule(facts -> true, facts -> System.out.print((int) facts.get("number")));
        Rule rule = AnyRules.anyOf(fizzBuzzRule, fizzRule, buzzRule, NonFizzBuzzRule);

        // fire rules
        Facts facts = new Facts();
        facts.put("number", 15);
        fizzBuzzEngine.fire(rule, facts);
        //        for (int i = 1; i <= 10; i++) {
        //            facts.put("number", i);
        //            fizzBuzzEngine.fire(rules, facts);
        //            System.out.println();
        //        }
    }

}
