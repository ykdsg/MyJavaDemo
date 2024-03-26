package com.hz.yk.base.algo.hot100;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/longest-consecutive-sequence/description/
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 * 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
 *
 * @author wuzheng.yk
 * @date 2024/3/25
 */
public class LC128longest_consecutive {

    /**
     * 左右指针法，因为在连续的时候每次都要判断max ，性能没有下面的方法好
     *
     * @param nums
     * @return
     */
    public int longestConsecutive1(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        Arrays.sort(nums);
        int max = 1;
        for (int right = 1, left = 0; right < nums.length; right++) {
            final int dif = nums[right] - nums[right - 1];
            if (dif == 0) { //前后是相同的情况
                left++;
            } else if (dif == 1) {
                max = Math.max(max, right - left + 1);
            } else {
                left = right;
            }
        }
        return max;
    }

    public int longestConsecutive(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        Arrays.sort(nums);
        int max = 1, pathSize = 1;
        for (int i = 1; i < nums.length; i++) {
            final int dif = nums[i] - nums[i - 1];
            if (dif == 0) { // 值是相同的情况，说明序列还是能够连上的，但是存在重复值对长度没影响
                continue;
            } else if (dif == 1) { // 连续的情况
                pathSize++;
            } else { // 说明不连续
                max = Math.max(max, pathSize);
                pathSize = 1; //重新开始计算
            }
        }
        return Math.max(max, pathSize);
    }
}
