package com.hz.yk.base.algo.hot100.practice2;

/**
 * https://leetcode.cn/problems/climbing-stairs/description/
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 *
 * @author wuzheng.yk
 * @date 2024/2/23
 */
public class LC70climbing_stairs {

    public int climbStairs(int n) {
        if (n < 3) {
            return n;
        }
        int step1 = 1, step2 = 2;
        int step = 0;
        // 注意这里的=
        for (int i = 3; i <= n; i++) {
            step = step1 + step2;
            step1 = step2;
            step2 = step;
        }
        return step;
    }

}
