package com.hz.yk.base.algo.hot100.practice2;

/**
 * https://leetcode.cn/problems/maximum-subarray/description/
 * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * 子数组 是数组中的一个连续部分。
 *
 * @author wuzheng.yk
 * @date 2024/2/21
 */
public class LC53maximum_subarray {

    /**
     * 关键是动态数组的定义
     * dp[i] 表示包含i 连续子数组最大和
     * dp[i]=max(dp[i]+i,i)
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            // 这样的写法比max 有较大性能提升
            dp[i] = dp[i - 1] < 0 ? nums[i] : (dp[i - 1] + nums[i]);
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}
