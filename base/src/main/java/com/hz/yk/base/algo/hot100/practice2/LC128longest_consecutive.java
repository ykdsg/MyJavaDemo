package com.hz.yk.base.algo.hot100.practice2;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/longest-consecutive-sequence/description/
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 * 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
 *
 * @author wuzheng.yk
 * @date 2024/4/2
 */
public class LC128longest_consecutive {

    public int longestConsecutive1(int[] nums) {
        if (nums.length < 2) {
            return nums.length;
        }

        Arrays.sort(nums);
        int len = 1, max = 1;
        for (int i = 1; i < nums.length; i++) {
            final int dif = nums[i] - nums[i - 1];
            if (dif == 1) {
                len++;
            } else if (dif == 0) { // 重复的情况
                continue;
            } else {
                max = Math.max(max, len);
                len = 1;
            }
        }
        max = Math.max(max, len);
        return max;
    }

}
