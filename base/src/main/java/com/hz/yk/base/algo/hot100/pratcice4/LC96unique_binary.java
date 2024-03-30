package com.hz.yk.base.algo.hot100.pratcice4;

/**
 * https://leetcode.cn/problems/unique-binary-search-trees/description/
 * 给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？返回满足题意的二叉搜索树的种数。
 *
 * @author wuzheng.yk
 * @date 2024/3/30
 */
public class LC96unique_binary {

    /**
     * f(i) 表示以第i个为root 的数量
     * g(n) 表示n个节点组成树的数量
     * g(n) = f(1)+f(2)+...+f(n)
     * f(i) = g(i-1)*g(n-i)
     *
     * @param n
     * @return
     */
    public int numTrees(int n) {
        if (n < 2) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        // k表示多少个节点组成的数量
        for (int k = 2; k <= n; k++) {
            // 这里要计算f(i) ，所以是从1开始
            for (int i = 1; i <= k; i++) {
                dp[k] += dp[i - 1] * dp[k - i];
            }
        }
        return dp[n];

    }

}
