package com.hz.yk.algo.camp.ch12_dynamic.practice1;

/**
 * 给你一个整数数组 prices ，其中 prices[i] 表示某支股票第 i 天的价格。
 * 在每一天，你可以决定是否购买和/或出售股票。你在任何时候 最多 只能持有 一股 股票。你也可以先购买，然后在 同一天 出售。
 *
 * @author wuzheng.yk
 * @date 2023/7/29
 */
public class LC122best_time_to_buy_and_sell_stock2 {

    //设计状态还是为第i天，未持有或持有股票的资金情况
    // dp[i][0]=max(dp[i-1][0] , prices(i) +dp[i-1][1])
    // dp[i][1]=max(dp[i-1][0]-prices[i], dp[i-1][1])
    public int maxProfit(int[] prices) {
        int[][] dp = new int[prices.length][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], prices[i] + dp[i - 1][1]);
            dp[i][1] = Math.max(dp[i - 1][0] - prices[i], dp[i - 1][1]);
        }
        return dp[prices.length - 1][0];
    }

    public static void main(String[] args) {
        LC122best_time_to_buy_and_sell_stock2 test = new LC122best_time_to_buy_and_sell_stock2();
        final int result = test.maxProfit(new int[]{ 7, 1, 5, 3, 6, 4 });
        System.out.println(result);
    }
}
