package com.hz.yk.algo.camp.ch11_binary.practice1;

/**
 * 给你一个非负整数 x ，计算并返回 x 的 算术平方根
 * 
 * https://leetcode.cn/problems/sqrtx/
 * @author wuzheng.yk
 * @date 2023/8/2
 */
public class LC69sqrtx {
    public int mySqrt(int x) {
        int left = 0, right = x, result = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (mid * mid <= x) {
                //说明mid还比较小，可以往右边移动 
                result = mid;
                left = mid + 1;
            }else {
                right = mid - 1;
            } 
        }
        return result;
    }

    public static void main(String[] args) {
        LC69sqrtx test = new LC69sqrtx();
        final int sqrt8 = test.mySqrt(8);
        System.out.println(sqrt8);

        final int sqrt4 = test.mySqrt(4);
        System.out.println(sqrt4);
    }

}
