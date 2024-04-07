package com.hz.yk.base.algo.hot100.practice3;

/**
 * https://leetcode.cn/problems/best-time-to-buy-and-sell-stock/description/
 * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
 * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
 * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
 *
 * @author wuzheng.yk
 * @date 2024/4/7
 */
public class LC121best_time {

    public int maxProfit(int[] prices) {
        final int len = prices.length;
        if (len < 2) {
            return 0;
        }
        //dp[i][0] 表示第i天 不持有的情况，dp[i][1]表示持有的情况
        int[][] dp = new int[len][2];
        dp[0][1] = -prices[0];
        for (int i = 1; i < len; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], prices[i] + dp[i - 1][1]);
            dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
        }
        return dp[len - 1][0];
    }

}
