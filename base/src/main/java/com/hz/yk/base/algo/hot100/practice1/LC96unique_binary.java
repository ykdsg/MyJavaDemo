package com.hz.yk.base.algo.hot100.practice1;

/**
 * https://leetcode.cn/problems/unique-binary-search-trees/description/
 * 给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？返回满足题意的二叉搜索树的种数。
 *
 * @author wuzheng.yk
 * @date 2024/3/1
 */
public class LC96unique_binary {

    /**
     * f(i) 表示以第i个节点 为root 的数量，
     * G(n) 表示n个节点组成的数量
     * G(n)=f(1)+f(2)+...+f(n)
     * f(i) = G(i-1)*G(n-i)
     * G(n) = G(0)*G(n-1)+G(1)*G(n-2)+...+G(n-1)*G(0)
     *
     * @param n
     * @return
     */
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        //0 或1个节点自由1种
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                dp[i] += dp[j] * dp[i - j - 1];
            }
        }
        return dp[n];

    }

}
