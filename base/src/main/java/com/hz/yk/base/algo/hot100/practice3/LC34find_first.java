package com.hz.yk.base.algo.hot100.practice3;

/**
 * https://leetcode.cn/problems/find-first-and-last-position-of-element-in-sorted-array/description/
 * 给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。
 * 如果数组中不存在目标值 target，返回 [-1, -1]。
 * 你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。
 *
 * @author wuzheng.yk
 * @date 2024/2/11
 */
public class LC34find_first {

    public int[] searchRange(int[] nums, int target) {
        final int len = nums.length;
        if (len == 0) {
            return new int[]{ -1, -1 };
        }
        //注意这里的初始值
        int begin = -1, end = -1;
        int left = 0, right = len - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                begin = mid;
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        left = 0;
        right = len - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                end = mid;
                left = mid + 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return new int[]{ begin, end };
    }
}
