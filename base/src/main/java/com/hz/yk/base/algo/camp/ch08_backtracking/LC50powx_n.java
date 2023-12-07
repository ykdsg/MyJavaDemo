package com.hz.yk.base.algo.camp.ch08_backtracking;

/**
 * 实现 pow(x, n) ，即计算 x 的整数 n 次幂函数
 * https://leetcode.cn/problems/powx-n/
 * @author wuzheng.yk
 * @date 2023/7/25
 */
public class LC50powx_n {
    public double myPow(double x, int n) {
        //因为负数转正数可能存在溢出的情况，所以这里需要使用long
        long N = n;
        return N >= 0 ? quickMul(x, N) : 1 / quickMul(x, -N);
    }

    public double quickMul(double x, long n) {
        if (n == 0) {
            return 1;
        }
        final double y = quickMul(x, n / 2);
        return n % 2 == 0 ? y * y : y * y * x;

    }

    public static void main(String[] args) {
        LC50powx_n test = new LC50powx_n();
        final double result = test.myPow(2.0, -2147483648);
        System.out.println(result);
    }
}
