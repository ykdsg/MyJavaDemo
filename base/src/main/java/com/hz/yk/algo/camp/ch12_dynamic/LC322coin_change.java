package com.hz.yk.algo.camp.ch12_dynamic;

import java.util.Arrays;

/**
 * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
 * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
 * 你可以认为每种硬币的数量是无限的。
 * https://leetcode.cn/problems/coin-change/description/
 *
 * @author wuzheng.yk
 * @date 2023/7/28
 */
public class LC322coin_change {

    //递推公式 f[amount]=min(f[amount-1],f[amount-2],f[amount-5])+1
    public int coinChange(int[] coins, int amount) {
        //同样这里为了方便理解，自动扩展1个长度
        int[] dp = new int[amount + 1];
        // 注意这里的初始化值，代表没找到
        Arrays.fill(dp, amount+1);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        LC322coin_change test = new LC322coin_change();
        final int[] coins = { 2 };
        final int result11 = test.coinChange(coins, 10);
        System.out.println("result11=" + result11);
        //System.out.println("result 23="+test.coinChange(coins, 23));
    }
}
