package com.hz.yk.algo.camp.ch11_binary;

/**
 * 已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。例如，原数组 nums = [0,1,2,4,5,6,7] 在变化后可能得到：
 * 若旋转 4 次，则可以得到 [4,5,6,7,0,1,2]
 * 若旋转 7 次，则可以得到 [0,1,2,4,5,6,7]
 * https://leetcode.cn/problems/find-minimum-in-rotated-sorted-array/
 *
 * @author wuzheng.yk
 * @date 2023/8/1
 */
public class LC153find_minimum_in_rotated_sorted_array {

    public int findMin(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            if (left == right) {
                return nums[left];
            }
            if (right - left == 1) {
                return Math.min(nums[left], nums[right]);
            }
            if (nums[left] < nums[right]) {
                return nums[left];
            } else {
                int half = left + (right - left) / 2;
                //这里没必要判断，上面right-left ==1 已经涵盖这个情况
                if (left == half) {
                    return nums[left];
                }
                //说明在另一边
                if (nums[left] < nums[half]) {
                    left = half;
                } else {
                    right = half;
                }
            }
        }

        return 0;
    }

    public int findMin2(int[] nums) {
        return calc(nums, 0, nums.length - 1);
    }

    public int calc(int[] nums, int left, int right) {
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
        }else{
            int half = left + (right - left) / 2;
            if (nums[left] >= nums[half]) {
                return calc(nums, left, half);
            }else {
                return calc(nums, half, right);
            }
        }
    }

    public static void main(String[] args) {
        LC153find_minimum_in_rotated_sorted_array test = new LC153find_minimum_in_rotated_sorted_array();
        int[] int5 = new int[]{ 5, 6, 1, 2, 3, 4, 5 };
        final int min = test.findMin2(int5);
        System.out.println(min);
    }
}
