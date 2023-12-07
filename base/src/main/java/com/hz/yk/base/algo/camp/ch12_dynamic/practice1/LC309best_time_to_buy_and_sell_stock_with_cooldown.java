package com.hz.yk.base.algo.camp.ch12_dynamic.practice1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 在原题目的基础上增加限制条件：卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)
 *
 * @author wuzheng.yk
 * @date 2023/9/7
 */
public class LC309best_time_to_buy_and_sell_stock_with_cooldown {

    public int maxProfit(int[] prices) {
        if (prices.length < 2) {
            return 0;
        }
        // 动态方程：
        // dp[i][0]=max(dp[i-1][0],dp[i-1][1]+prices[i])
        // dp[i][1]=max(dp[i-1][1],dp[i-2][0]-prices[i])
        int[][] dp = new int[prices.length][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        dp[1][0] = Math.max(dp[0][0], dp[0][1] + prices[1]);
        dp[1][1] = Math.max(-prices[0], -prices[1]);
        for (int i = 2; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 2][0] - prices[i]);
        }

        return dp[prices.length - 1][0];
    }

    @Test
    public void test() {
        LC309best_time_to_buy_and_sell_stock_with_cooldown test = new LC309best_time_to_buy_and_sell_stock_with_cooldown();
        int[] prices = new int[]{ 1, 2, 3, 0, 2 };
        final int result = test.maxProfit(prices);
        assertEquals(3, result);
    }

}
