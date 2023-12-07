package com.hz.yk.base.algo.camp.ch12_dynamic;

/**
 * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
 *
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * @author wuzheng.yk
 * @date 2023/7/29
 */
public class LC123best_time_to_buy_and_sell_stock3 {
    //核心是梳理状态，以及状态对应的选择
    //dp[i][k][0 1]，表示第i天，最多允许k次操作，持有（1）或者没有（0）的情况下最大利润
    //dp[i][k][0]=max(dp[i-1][k][0] , dp[i-1][k][1]+prices[i]) ，第i天没持有的情况，可能是i-1 天没持有，也可能是i-1持有现在卖出
    //dp[i][k][1]=max(dp[i-1][k][1],dp[i-1][k-1][0]-prices[i])，第i天持有的情况，可能是i-1 天就持有，也可能是i-1没持有现在买入
    //
    public int maxProfit(int[] prices) {
        final int n = prices.length;
        int max_k = 2;
        int[][][] dp = new int[n][max_k+1][2];
        for (int i = 0; i < n; i++) {
            for (int k = max_k; k >= 1; k--) {
                if (i == 0) {
                    dp[0][k][0] = 0;
                    dp[0][k][1] = -prices[0];
                    continue;
                }
                dp[i][k][0] = Math.max(dp[i - 1][k][0], dp[i - 1][k][1] + prices[i]);
                dp[i][k][1] = Math.max(dp[i - 1][k][1], dp[i - 1][k - 1][0] - prices[i]);
            }
        }
        //最后一天，最多允许 K 次交易，最多获得多少利润
        return dp[n - 1][max_k][0];
    }

    public int maxProfit2(int[] prices) {
        //初始化的情况相当于i=-1,不允许交易的情况下，是不可能持有股票的，用最小值表示这种不可能。
        int dp_i10 = 0, dp_i11 = Integer.MIN_VALUE;
        int dp_i20 = 0, dp_i21 = Integer.MIN_VALUE;
        for (int price : prices) {
            dp_i20 = Math.max(dp_i20, dp_i21 + price);
            dp_i21 = Math.max(dp_i21, dp_i10 - price);
            dp_i10 = Math.max(dp_i10, dp_i11 + price);
            // 这里参考上面的公式 dp[i-1][k-1][0] ，k=1的时候这块等于0，所以可以简化
            dp_i11 = Math.max(dp_i11, -price);
        }

        return dp_i20;
    }

    public static void main(String[] args) {
        LC123best_time_to_buy_and_sell_stock3 test = new LC123best_time_to_buy_and_sell_stock3();
        final int result = test.maxProfit(new int[]{ 3, 3, 5, 0, 0, 3, 1, 4 });
        System.out.println(result);
    }
}
