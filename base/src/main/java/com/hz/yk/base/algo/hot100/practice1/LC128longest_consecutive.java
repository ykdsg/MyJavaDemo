package com.hz.yk.base.algo.hot100.practice1;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/longest-consecutive-sequence/description/
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 * 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
 *
 * @author wuzheng.yk
 * @date 2024/3/27
 */
public class LC128longest_consecutive {

    public int longestConsecutive(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        Arrays.sort(nums);
        int max = 1, pathLen = 1;
        for (int i = 1; i < nums.length; i++) {
            final int dif = nums[i] - nums[i - 1];
            if (dif == 1) {
                pathLen++;
            } else if (dif == 0) {
                continue;
            } else {
                max = Math.max(max, pathLen);
                pathLen = 1; // 不连续的情况下复位
            }
        }
        return Math.max(max, pathLen); //最后还要再比较下，因为存在一致连续的情况
    }

}
