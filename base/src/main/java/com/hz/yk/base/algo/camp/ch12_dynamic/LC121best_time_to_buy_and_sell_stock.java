package com.hz.yk.base.algo.camp.ch12_dynamic;

/**
 * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
 * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
 * https://leetcode.cn/problems/best-time-to-buy-and-sell-stock/#/description
 *
 * @author wuzheng.yk
 * @date 2023/7/28
 */
public class LC121best_time_to_buy_and_sell_stock {

    //递推方程：f[i]=prices[i]-min(price[0...i])
    //相当于第i 天股票最大收益，应该是前i-1 天的最小值，跟第i天的价格增幅
    public int maxProfit(int[] prices) {
        final int n = prices.length;
        int min = prices[0];
        int max = 0;
        for (int i = 1; i < n; i++) {
            max = Math.max(max, prices[i] - min);
            min = Math.min(min, prices[i]);
        }
        return max;
    }

    public static void main(String[] args) {
        LC121best_time_to_buy_and_sell_stock test = new LC121best_time_to_buy_and_sell_stock();
        final int result = test.maxProfit(new int[]{ 7, 1, 5, 3, 6, 4 });
        System.out.println(result);
    }
}
