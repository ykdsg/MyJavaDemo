package com.hz.yk.base.algo.hot100;

/**
 * https://leetcode.cn/problems/find-first-and-last-position-of-element-in-sorted-array/description/
 * 给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。
 * 如果数组中不存在目标值 target，返回 [-1, -1]。
 * 你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。
 *
 * @author wuzheng.yk
 * @date 2024/2/4
 */
public class LC34find_first {

    /**
     * 核心关键是2次二分查找，以及每次在找到target 位置的时候不同的处理
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {
        if (nums.length == 0) {
            return new int[]{ -1, -1 };
        }
        int begin = -1, end = -1;
        int left = 0, right = nums.length - 1;
        // 找第一个等于target的位置
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                begin = mid;
                right = mid - 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        //找最后一个等于target 的位置
        left = 0;
        right = nums.length - 1;
        while (left <= right) {
            // 因为这里left 会设置为mid，所以这里需要进行+1 操作，不然会死循环
            int mid = left + (right - left + 1) / 2;
            if (nums[mid] == target) {
                end = mid;
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return new int[]{ begin, end };

    }

}
