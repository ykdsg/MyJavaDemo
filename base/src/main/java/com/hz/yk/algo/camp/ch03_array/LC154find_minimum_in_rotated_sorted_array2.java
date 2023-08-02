package com.hz.yk.algo.camp.ch03_array;

/**
 * 已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。例如，原数组 nums = [0,1,4,4,5,6,7] 在变化后可能得到：
 * 若旋转 4 次，则可以得到 [4,5,6,7,0,1,4]
 * 给你一个可能存在 重复 元素值的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的 最小元素 。
 * 
 * https://leetcode.cn/problems/find-minimum-in-rotated-sorted-array-ii/
 * @author wuzheng.yk
 * @date 2023/8/2
 */
public class LC154find_minimum_in_rotated_sorted_array2 {
    public int findMin(int[] nums) {
        return calc(nums, 0, nums.length - 1);
        
    }

    int calc(int[] nums, int left, int right) {
        if (nums.length == 0) {
            return 0;
        }
        if (left == right) {
            return nums[left];
        }
        if (right - left <= 1) {
            return Math.min(nums[left], nums[right]);
        }
        if (nums[left] < nums[right]) {
            return nums[left];
        }else {
            int half = left + (right - left) / 2;
            return Math.min(calc(nums, left, half), calc(nums, half, right));
        }
    }

    public static void main(String[] args) {
        LC154find_minimum_in_rotated_sorted_array2 test = new LC154find_minimum_in_rotated_sorted_array2();
        int[] ary = new int[]{ 2, 2, 2, 0, 1 };
        final int min = test.findMin(ary);
        System.out.println(min);
    }
}
