package com.hz.yk.base.algo.camp.ch12_dynamic.practice1;

/**
 * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 * @author wuzheng.yk
 * @date 2023/7/29
 */
public class LC123best_time_to_buy_and_sell_stock3 {

    //设计状态方程为dp[i][k][0/1] 代表当前资金情况，k表示最多剩余多少次交易机会
    //买入就算一次交易了
    //dp[i][k][0]=max(dp[i-1][k][0],dp[i-1][k][1]+prices[i])
    //dp[i][k][1] = max(dp[i-1][k][1],dp[i-1][k-1][0]-prices[i])
    public int maxProfit(int[] prices) {
        final int n = prices.length;
        int maxK = 2;
        int[][][] dp = new int[prices.length][maxK + 1][2];
        for (int i = 0; i < prices.length; i++) {
            for (int k = maxK; k >= 1; k--) {
                if (i == 0) {
                    dp[0][k][0] = 0;
                    dp[0][k][1] = -prices[0];
                } else {
                    dp[i][k][0] = Math.max(dp[i - 1][k][0], dp[i - 1][k][1] + prices[i]);
                    dp[i][k][1] = Math.max(dp[i - 1][k][1], dp[i - 1][k - 1][0] - prices[i]);
                }
            }
        }
        return dp[n - 1][maxK][0];
    }

    public static void main(String[] args) {
        LC123best_time_to_buy_and_sell_stock3 test = new LC123best_time_to_buy_and_sell_stock3();
        final int result = test.maxProfit(new int[]{ 3, 3, 5, 0, 0, 3, 1, 4 });
        System.out.println(result);
    }
}
