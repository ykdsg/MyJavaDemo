package com.hz.yk.base.algo.hot100.practice1;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/next-permutation/description/
 * 整数数组的一个 排列  就是将其所有成员以序列或线性顺序排列。
 * 整数数组的 下一个排列 是指其整数的下一个字典序更大的排列。更正式地，如果数组的所有排列根据其字典顺序从小到大排列在一个容器中，那么数组的 下一个排列 就是在这个有序容器中排在它后面的那个排列。如果不存在下一个更大的排列，那么这个数组必须重排为字典序最小的排列（即，其元素按升序排列）。
 * 给你一个整数数组 nums ，找出 nums 的下一个排列。
 *
 * @author wuzheng.yk
 * @date 2024/3/22
 */
public class LC031next_permutation {

    /**
     * 123456
     * 123465
     * 123546
     * 151
     *
     * @param nums
     */
    public void nextPermutation(int[] nums) {
        if (nums.length < 2) {
            return;
        }
        // 找到右边的小数
        int rightS = nums.length - 2;
        while (rightS >= 0 && nums[rightS] >= nums[rightS + 1]) {
            rightS--;
        }
        if (rightS >= 0) {
            //找到右边的大数
            int rightB = nums.length - 1;
            while (rightS < rightB && nums[rightB] <= nums[rightS]) {
                rightB--;
            }
            // 将大数换到小数的位置
            final int tmp = nums[rightS];
            nums[rightS] = nums[rightB];
            nums[rightB] = tmp;
        }
        //    要对小数后面的所有数字排序
        Arrays.sort(nums, rightS + 1, nums.length);
    }

}
