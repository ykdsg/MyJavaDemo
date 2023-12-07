package com.hz.yk.base.algo.camp.ch12_dynamic;

/**
 * 给你一个整数数组 prices ，其中 prices[i] 表示某支股票第 i 天的价格。
 * 在每一天，你可以决定是否购买和/或出售股票。你在任何时候 最多 只能持有 一股 股票。你也可以先购买，然后在 同一天 出售。
 * @author wuzheng.yk
 * @date 2023/7/29
 */
public class LC122best_time_to_buy_and_sell_stock2  {

    //这个在解决前一题的时候第一次给出的解法就适用于这种情况
    //f[i] = f[i-1]+max(0 ,prices[i] - prices[0])
    //也比较好理解，第i天的最好收益 是第i-1天的最好收益+ 第i天的涨幅（如果有的话）
    public int maxProfit(int[] prices) {
        if (prices.length == 1) {
            return 0;
        }
        int result = 0;
        for (int i = 1; i < prices.length; i++) {
            result = result + Math.max(0, prices[i] - prices[i - 1]);
        }
        return result;
    }

    public static void main(String[] args) {
        LC122best_time_to_buy_and_sell_stock2 test = new LC122best_time_to_buy_and_sell_stock2();
        final int result = test.maxProfit(new int[]{ 7, 1, 5, 3, 6, 4 });
        System.out.println(result);
    }
}
