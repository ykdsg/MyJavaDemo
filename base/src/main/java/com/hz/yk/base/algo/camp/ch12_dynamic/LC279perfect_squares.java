package com.hz.yk.base.algo.camp.ch12_dynamic;

/**
 * 给你一个整数 n ，返回 和为 n 的完全平方数的最少数量 。
 * 完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
 * https://leetcode.cn/problems/perfect-squares/
 * @author wuzheng.yk
 * @date 2023/7/30
 */
public class LC279perfect_squares {

    //递推方程：f[i]=min(f[i-sqrt(1..n)])+1
    public int numSquares(int n) {
        final double sqrt = Math.sqrt(n);
        int limit = (int) Math.floor(sqrt);
        int[] dp = new int[n+1];
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            int min = n;
            //完全平方数是从1开始
            for (int j = 1; j <= limit; j++) {
                if (j * j > i) {
                    break;
                }
                min = Math.min(min, dp[i - j*j]);
            }
            dp[i] = min + 1;
        }
        return dp[n];
    }

    public static void main(String[] args) {
        LC279perfect_squares test = new LC279perfect_squares();
        final int result = test.numSquares(12);
        System.out.println(result);

    }
}
