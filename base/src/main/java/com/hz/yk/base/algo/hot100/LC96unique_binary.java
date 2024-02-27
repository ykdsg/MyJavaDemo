package com.hz.yk.base.algo.hot100;

/**
 * https://leetcode.cn/problems/unique-binary-search-trees/description/
 * 给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？返回满足题意的二叉搜索树的种数。
 *
 * @author wuzheng.yk
 * @date 2024/2/27
 */
public class LC96unique_binary {

    /**
     * i 表示以第i个节点(从1开始)为根节点的子树，左子树个数为i，右子树个数为n-i-1
     * f(i) 表示i为根节点时组合个数，G(i) 表示i个节点存在的组合
     * G(n)=f(1)+f(2)+...+f(n)
     * f(i)=G(i−1)∗G(n−i)
     * G(n) = G(0)*G(n-1) + G(1)*G(n-2)+...+G(n-1)*G(0)
     * dp[n] 表示n 个节点的组合数
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
            //以第j个为 根节点时对应的组合数
            for (int j = 1; j <= i; j++) {
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }
        return dp[n];
    }

    /**
     * 递归解法，性能有点差，中间会存在很多重复计算，加个缓存就能提升很多
     *
     * @param n
     * @return
     */
    public int numTreesRecur(int n) {
        if (n < 2) {
            return 1;
        }
        int result = 0;
        for (int i = 0; i < n; i++) {
            final int sum = numTreesRecur(i) * numTreesRecur(n - i - 1);
            result += sum;
        }
        return result;
    }
}
