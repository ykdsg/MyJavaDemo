package com.hz.yk.base.algo.camp.ch07_recursive.practice1;

import java.util.HashMap;
import java.util.Map;

/**
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * @author wuzheng.yk
 * @date 2023/8/9
 */
public class LC70climbing_stairs {

    static Map<Integer, Integer> cache = new HashMap<>();
    public int climbStairs(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n <= 2) {
            return n;
        }
        final Integer cacheV = cache.get(n);
        if (cacheV != null) {
            return cacheV;
        }else {
            final int result = climbStairs(n - 1) + climbStairs(n - 2);
            cache.put(n, result);
            return result;
        }
    }

    public static void main(String[] args) {
        LC70climbing_stairs test = new LC70climbing_stairs();
        final int result = test.climbStairs(3);
        System.out.println(result);
    }
}
