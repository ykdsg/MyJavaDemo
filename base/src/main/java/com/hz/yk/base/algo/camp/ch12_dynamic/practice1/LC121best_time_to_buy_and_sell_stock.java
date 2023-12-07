package com.hz.yk.base.algo.camp.ch12_dynamic.practice1;

/**
 * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
 * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
 * https://leetcode.cn/problems/best-time-to-buy-and-sell-stock/#/description
 *
 * @author wuzheng.yk
 * @date 2023/7/28
 */
public class LC121best_time_to_buy_and_sell_stock {

    public int maxProfit(int[] prices) {
        //定义状态:dp[i][0/1] 第i天未持有、持有股票的资金情况
        //    动态方程：
        //    dp[i][0]=max(dp[i-1][0],dp[i-1][1]+price[i])
        //    dp[i][1] = max(dp[i-1][1],-price[i])
        //初始情况
        int m0 = 0;
        int m1 = -prices[0];
        int max = 0;
        for (int i = 1; i < prices.length; i++) {
            m0 = Math.max(m0, prices[i] + m1);
            m1 = Math.max(m1, -prices[i]);
            max = Math.max(max, m0);
        }
        return max;

    }

    public static void main(String[] args) {
        LC121best_time_to_buy_and_sell_stock test = new LC121best_time_to_buy_and_sell_stock();
        final int result = test.maxProfit(new int[]{ 7, 1, 5, 3, 6, 4 });
        System.out.println(result);
    }
}
