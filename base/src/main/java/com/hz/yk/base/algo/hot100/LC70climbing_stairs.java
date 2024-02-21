package com.hz.yk.base.algo.hot100;

/**
 * https://leetcode.cn/problems/climbing-stairs/description/
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 *
 * @author wuzheng.yk
 * @date 2024/2/19
 */
public class LC70climbing_stairs {

    /**
     * 可以用递归，也可以用动态规划 dp[i] = dp[i-1]+dp[i-2]
     *
     * @param n
     * @return
     */

    public int climbStairs(int n) {
        if (n < 3) {
            return n;
        }
        int pre1 = 1, pre2 = 2;
        int cur = 0;
        for (int i = 3; i <= n; i++) {
            cur = pre1 + pre2;
            pre1 = pre2;
            pre2 = cur;
        }
        return cur;
    }

    /**
     * 使用递归的方法，这个写法是简便，但是会超时，因为有大量中间过程需要重复计算
     * 可以通过HashMap 来进行缓存
     *
     * @param n
     * @return
     */
    public int climbStairs2(int n) {
        if (n < 3) {
            return n;
        }
        return climbStairs2(n - 1) + climbStairs2(n - 2);
    }
}
