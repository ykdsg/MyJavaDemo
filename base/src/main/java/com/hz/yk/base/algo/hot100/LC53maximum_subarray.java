package com.hz.yk.base.algo.hot100;

/**
 * https://leetcode.cn/problems/maximum-subarray/description/
 * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * 子数组 是数组中的一个连续部分。
 *
 * @author wuzheng.yk
 * @date 2024/2/17
 */
public class LC53maximum_subarray {

    /**
     * -1,-2,3,4,-9,6
     * 动态规划，dp[i] 表示包含下标i的连续子数组最大和，dp[i] =Max(i,dp[i-1]+i)
     * 因为只跟i-1 相关因此可以简化为单个变量
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int max = dp[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);
            max = Math.max(max, dp[i]);
        }
        return max;
    }

}
