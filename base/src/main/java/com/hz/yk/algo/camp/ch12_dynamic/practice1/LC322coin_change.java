package com.hz.yk.algo.camp.ch12_dynamic.practice1;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

        int[] dp = new int[amount + 1];
        // 因为下面会进行+1操作，所以这里不能直接使用MAX
        //Arrays.fill(dp, Integer.MAX_VALUE);
        //注意这里不能直接用amount，必须要比amount大
        Arrays.fill(dp, amount+1);
        dp[0] = 0;
        for (int i = 0; i <= amount; i++) {
            for (int coin : coins) {
                //注意这里必须有=
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
        final int result11 = test.coinChange(coins, 11);
        System.out.println("result11=" + result11);
        //System.out.println("result 23="+test.coinChange(coins, 23));
    }

    @Test
    public void test() {
        LC322coin_change test = new LC322coin_change();
        int[] coins = { 2, 5 };
        final int result20 = test.coinChange(coins, 20);
        assertEquals(4, result20);
        final int result11 = test.coinChange(coins, 11);
        assertEquals(4, result11);
        final int result3 = test.coinChange(coins, 3);
        assertEquals(-1, result3);  

    }
}
