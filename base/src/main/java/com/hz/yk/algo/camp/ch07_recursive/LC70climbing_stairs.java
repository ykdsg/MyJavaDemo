package com.hz.yk.algo.camp.ch07_recursive;

import java.util.HashMap;
import java.util.Map;

/**
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 *
 * @author wuzheng.yk
 * @date 2023/7/3
 */
public class LC70climbing_stairs {

    private final Map<Integer, Integer> cache = new HashMap<>();

    /**
     * 使用递归+ 缓存的方式
     *
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        Integer result = cache.get(n);
        if (result != null) {
            return result;
        }

        if (n == 1 || n == 2) {
            result = n;
        } else {
            result = climbStairs(n - 1) + climbStairs(n - 2);
        }
        cache.put(n, result);
        return result;
    }

    /**
     * 不使用递归，整体效率跟使用缓存的递归差不多
     * @param n
     * @return
     */
    public int climbStairs2(int n) {
        if (n <= 2) {
            return n;
        }
        int i1 = 1;
        int i2 = 2;
        int i3 = 0;
        for (int i = 2; i < n; i++) {
            i3 = i1 + i2;
            //交换缓冲区位置
            i1 = i2;
            i2 = i3;
        }
        return i3;
    }
}
