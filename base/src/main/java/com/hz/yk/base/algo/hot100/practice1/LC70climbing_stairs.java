package com.hz.yk.base.algo.hot100.practice1;

/**
 * https://leetcode.cn/problems/climbing-stairs/description/
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 *
 * @author wuzheng.yk
 * @date 2024/3/3
 */
public class LC70climbing_stairs {

    public int climbStairs(int n) {
        if (n < 3) {
            return n;
        }
        int one = 1;
        int two = 2;
        for (int i = 3; i <= n; i++) {
            int cur = one + two;
            one = two;
            two = cur;
        }
        return two;
    }

}
