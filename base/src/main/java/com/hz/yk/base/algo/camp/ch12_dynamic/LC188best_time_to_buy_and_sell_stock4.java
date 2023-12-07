package com.hz.yk.base.algo.camp.ch12_dynamic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 给你一个整数数组 prices 和一个整数 k ，其中 prices[i] 是某支给定的股票在第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。也就是说，你最多可以买 k 次，卖 k 次。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-iv/
 *
 * @author wuzheng.yk
 * @date 2023/8/30
 */
public class LC188best_time_to_buy_and_sell_stock4 {

    public int maxProfit(int k, int[] prices) {
        if (k < 1) {
            return 0;
        }
        //核心是对状态的定义dp[第i天][最多k次交易][没有持有0，持有1]
        //然后是状态转移方程：
        //dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1]+prices[i])
        //dp[i][k][1] = max(dp[i-1][k][1],dp[i-1][k-1][0]-prices[i])
        //最后是考虑清楚基础情况：
        //dp[0][k][0]=0, dp[0][k][1]=-prices[0]
        final int n = prices.length;
        int[][][] dp = new int[n][k+1][2];
        for (int i = 0; i < n; i++) {
            for (int j = k; j >= 1; j--) {
                if (i == 0) {
                    dp[0][k][0] = 0;
                    dp[0][k][1] = -prices[0];
                    continue;
                }
                dp[i][k][0] = Math.max(dp[i - 1][k][0], dp[i - 1][k][1] + prices[i]);
                dp[i][k][1] = Math.max(dp[i - 1][k][1], dp[i - 1][k - 1][0] - prices[i]);
            }
        }

        return dp[n - 1][k][0];
    }

    @Test
    public void test(){
        LC188best_time_to_buy_and_sell_stock4 test = new LC188best_time_to_buy_and_sell_stock4();
        int[] prices = new int[]{ 2, 4, 1 };
        final int result = test.maxProfit(2, prices);
        assertEquals(2, result);
    }
}
