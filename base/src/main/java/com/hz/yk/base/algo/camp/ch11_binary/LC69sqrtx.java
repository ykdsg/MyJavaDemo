package com.hz.yk.base.algo.camp.ch11_binary;

/**
 * 给你一个非负整数 x ，计算并返回 x 的 算术平方根
 *
 * https://leetcode.cn/problems/sqrtx/
 * @author wuzheng.yk
 * @date 2023/8/2
 */
public class LC69sqrtx {
    public int mySqrt(int x) {
        int left = 0, right = x, ans = -1;
        while (left <= right) {
            int half = left + (right - left) / 2;
            //只要half** <=x，left左边界就会一直向右移动，ans就会一直更新（变大），直到不在满足mid <= x/mid的条件为止，
            // ans就会停止更新，永远停在满足mid<=x/mid条件下面的最后一次更新，即满足ans * ans <= mid的最大值。
            if (half * half <= x) {
                ans = half;
                left = half + 1;
            }else {
                // 说明half** 比较大，那就需要在小的这边继续逼近
                right = half-1;
            }
        }
        return ans;
    }

}
