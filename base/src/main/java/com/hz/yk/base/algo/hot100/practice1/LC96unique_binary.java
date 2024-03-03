package com.hz.yk.base.algo.hot100.practice1;

/**
 * https://leetcode.cn/problems/unique-binary-search-trees/description/
 * 给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？返回满足题意的二叉搜索树的种数。
 *
 * @author wuzheng.yk
 * @date 2024/3/2
 */
public class LC96unique_binary {

    /**
     * f(i) 表示以第i个节点为root的数量
     * g(n) 表示n个节点组成不同的组合数量
     * g(n)=f(1)+f(2)+...+f(n)
     * f(i) = g(i-1)*g(n-i)
     * 推导出：g(n) = g(0)*g(n-1)+g(1)*g(n-2)+...+g(n-1)*g(0)
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
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }
        return dp[n];
    }

}
