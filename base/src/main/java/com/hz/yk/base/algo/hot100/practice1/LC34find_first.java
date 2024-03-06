package com.hz.yk.base.algo.hot100.practice1;

/**
 * https://leetcode.cn/problems/find-first-and-last-position-of-element-in-sorted-array/description/
 * 给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。
 * 如果数组中不存在目标值 target，返回 [-1, -1]。
 * 你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。
 *
 * @author wuzheng.yk
 * @date 2024/3/6
 */
public class LC34find_first {

    public int[] searchRange(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        int begin = getLocation(nums, target, left, right, true);
        int end = getLocation(nums, target, left, right, false);
        return new int[]{ begin, end };

    }

    /**
     * 找到target 对应的下标
     *
     * @param nums
     * @param target
     * @param left
     * @param right
     * @param isBegin 是否起始点
     * @return
     */
    private int getLocation(int[] nums, int target, int left, int right, boolean isBegin) {
        int location = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (target == nums[mid]) {
                location = mid;
                //关键点在于找起止点的时候对左右游标的处理
                if (isBegin) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else if (target < nums[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return location;
    }

}
