package com.hz.yk.base.algo.hot100.practice1;

/**
 * https://leetcode.cn/problems/maximum-subarray/description/
 * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * 子数组 是数组中的一个连续部分。
 *
 * @author wuzheng.yk
 * @date 2024/3/6
 */
public class LC53maximum_subarray {

    /**
     * dp[i] 表示以i为终点的最大和
     * 写出来之后发现可以直接使用一个变量pre 来代替动态数组
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int pre = nums[0];
        int max = nums[0];
        int cur;
        for (int i = 1; i < nums.length; i++) {
            cur = Math.max(nums[i], pre + nums[i]);
            max = Math.max(max, cur);
            pre = cur;
        }
        return max;
    }

}
