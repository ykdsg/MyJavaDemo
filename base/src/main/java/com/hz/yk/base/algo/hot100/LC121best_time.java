package com.hz.yk.base.algo.hot100;

/**
 * https://leetcode.cn/problems/best-time-to-buy-and-sell-stock/description/
 * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
 * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
 * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
 *
 * @author wuzheng.yk
 * @date 2024/3/20
 */
public class LC121best_time {

    /**
     * 我们需要做的是在第i天的时候，知道前面i-1 天最低价格是多少，然后在i天卖出的收益是多少。
     * 也就是维护一个最小值，跟当前价格进行比较
     *
     * @param prices
     * @return
     */
    public int maxProfit1(int[] prices) {
        int max = 0;
        int minPrice = prices[0];
        for (int i = 0; i < prices.length; i++) {
            max = Math.max(max, prices[i] - minPrice);
            minPrice = Math.min(minPrice, prices[i]);
        }
        return max;
    }

    /**
     * 上面的解法没有普适性
     *
     * @param prices
     * @return
     */
    public int maxProfit2(int[] prices) {
        final int len = prices.length;
        if (len < 2) {
            return 0;
        }
        //dp[i][0] 表示i这天不持有股票时最大现金情况，dp[i][1] 表示i这天持有股票最大现金情况
        int[][] dp = new int[len][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0]; //表示购买了第一天的股票
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]); // 要么是前一天也没持有，要么是卖掉持有的
            dp[i][1] = Math.max(dp[i - 1][1], -prices[i]); // 因为只能交易一次，所以这里不能用 dp[i-1][0] - prices[i]
        }
        //最大值肯定是最后一天不持有股票时最大现金数
        return dp[len - 1][0];
    }

}
