package com.hz.yk.base.algo.hot100.practice3;

/**
 * https://leetcode.cn/problems/maximum-subarray/description/
 * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * 子数组 是数组中的一个连续部分。
 *
 * @author wuzheng.yk
 * @date 2024/2/25
 */
public class LC53maximum_subarray {

    public int maxSubArray(int[] nums) {
        int pre = nums[0], max = nums[0];
        int cur = pre;
        //dp[i]=max(dp[i-1],0)+i
        for (int i = 1; i < nums.length; i++) {
            cur = Math.max(pre, 0) + nums[i];
            pre = cur;
            max = Math.max(max, cur);
        }
        return max;
    }
}
