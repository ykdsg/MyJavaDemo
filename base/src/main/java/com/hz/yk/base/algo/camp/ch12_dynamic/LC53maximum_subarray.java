package com.hz.yk.base.algo.camp.ch12_dynamic;

/**
 * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * https://leetcode.cn/problems/maximum-subarray/
 *
 * @author wuzheng.yk
 * @date 2023/7/28
 */
public class LC53maximum_subarray {

    // 核心是找到递推方程：f[i]=Max(0,f[i-1])+nums[i]，表示包含自身时最大连续子数组的值（不包含的时候就是i-1，所以这里一定要算自身）
    public int maxSubArray(int[] nums) {
        final int length = nums.length;
        if (length == 1) {
            return nums[0];
        }
        // 可以优化为单个数
        //int[] dp = new int[length];
        //dp[0] = nums[0];
        int dp = nums[0];
        int max = nums[0];
        for (int i = 1; i < length; i++) {
            dp = Math.max(dp, 0) + nums[i];
            max = Math.max(max, dp);
        }

        return max;
    }

    public static void main(String[] args) {
        LC53maximum_subarray test = new LC53maximum_subarray();
        final int result = test.maxSubArray(new int[]{ -2, 1, -3, 4, -1, 2, 1, -5, 4 });
        System.out.println(result);

    }
}
