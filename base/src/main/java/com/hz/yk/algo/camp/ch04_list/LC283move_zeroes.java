package com.hz.yk.algo.camp.ch04_list;

import java.util.Arrays;

/**
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * https://leetcode.cn/problems/move-zeroes/
 * @author wuzheng.yk
 * @date 2023/7/31
 */
public class LC283move_zeroes {
    public void moveZeroes(int[] nums) {
        int zeroIndex = -1;
        for (int i = 0; i < nums.length; i++) {
            if (zeroIndex < 0 && nums[i] == 0) {
                zeroIndex = i;
            } else if (nums[i] != 0 && zeroIndex >= 0) {
                nums[zeroIndex] = nums[i];
                nums[i] = 0;
                zeroIndex++;
            }
        }
    }

    public static void main(String[] args) {
        LC283move_zeroes test = new LC283move_zeroes();
        final int[] nums = new int[]{ 0, 1, 0, 3, 12 };
        test.moveZeroes(nums);
        System.out.println(Arrays.toString(nums));
        
    }
}
