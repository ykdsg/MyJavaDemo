package com.hz.yk.base.algo.hot100.practice3;

/**
 * https://leetcode.cn/problems/unique-binary-search-trees/description/
 * 给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？返回满足题意的二叉搜索树的种数。
 *
 * @author wuzheng.yk
 * @date 2024/4/21
 */
public class LC96unique_binary {

    /**
     * g(n) 表示n 个节点组合的数量
     * f(i) 表示第i 个节点为根节点的可能组合数量
     * f(i) = g(i-1)*g(n-i)
     * g(n) = f(1)+f(2)+...+f(n)
     * g(n) = g(0)*g(n-1)+g(1)*g(n-2)+...+g(n-1)*g(0)
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
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }
        return dp[n];
    }

}
