package com.hz.yk.base.algo.camp.ch10_greedy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 在柠檬水摊上，每一杯柠檬水的售价为 5 美元。顾客排队购买你的产品，（按账单 bills 支付的顺序）一次购买一杯。
 * 每位顾客只买一杯柠檬水，然后向你付 5 美元、10 美元或 20 美元。你必须给每个顾客正确找零，也就是说净交易是每位顾客向你支付 5 美元。
 * 注意，一开始你手头没有任何零钱。
 * 给你一个整数数组 bills ，其中 bills[i] 是第 i 位顾客付的账。如果你能给每位顾客正确找零，返回 true ，否则返回 false 。
 *
 * @author wuzheng.yk
 * @date 2023/8/7
 */
public class LC860lemonade_change {

    public boolean lemonadeChange(int[] bills) {
        //5元和10元的钞票数量
        int fiveCount = 0, tenCount = 0;
        for (int bill : bills) {
            if (bill == 5) {
                fiveCount++;
            } else if (bill == 10) {
                if (fiveCount == 0) {
                    return false;
                } else {
                    fiveCount--;
                    tenCount++;
                }
            } else {
                //    收到20元的情况
                if (tenCount > 0 && fiveCount > 0) {
                    fiveCount--;
                    tenCount--;
                } else if (fiveCount > 2) {
                    fiveCount -= 3;
                }else {
                    return false;
                }
            }
        }
        return true;
    }

    @Test
    public void test(){
        LC860lemonade_change test = new LC860lemonade_change();
        int[] ary = new int[]{ 5, 5, 5, 5, 10, 5, 10, 10, 10, 20 };
        final boolean resultTrue = test.lemonadeChange(ary);
        assertTrue(resultTrue);
    }
}
