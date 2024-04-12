package com.hz.yk.base.algo.hot100.practice2;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/next-permutation/description/
 * 整数数组的一个 排列  就是将其所有成员以序列或线性顺序排列。
 * 整数数组的 下一个排列 是指其整数的下一个字典序更大的排列。更正式地，如果数组的所有排列根据其字典顺序从小到大排列在一个容器中，那么数组的 下一个排列 就是在这个有序容器中排在它后面的那个排列。如果不存在下一个更大的排列，那么这个数组必须重排为字典序最小的排列（即，其元素按升序排列）。
 * 给你一个整数数组 nums ，找出 nums 的下一个排列。
 *
 * @author wuzheng.yk
 * @date 2024/4/12
 */
public class LC031next_permutation {

    /**
     * 123456 -> 123465 ->123546 -> 123645 -> 123654 -> 124356
     * 654321
     *
     * @param nums
     */
    public void nextPermutation(int[] nums) {
        final int len = nums.length;
        if (len < 2) {
            return;
        }

        int rightMin = len - 2;
        while (rightMin >= 0 && nums[rightMin] >= nums[rightMin + 1]) {
            rightMin--;
        }
        if (rightMin >= 0) { //说明不是最后一种情况
            //找到rightMin 右边大于自己的最小的数，因为每次都会基于rightMin+1 进行重新排序，所以第一个遇到大于rightMin的就是刚好大于并且是最小的
            int rightMax = len - 1;
            while (nums[rightMin] >= nums[rightMax]) {
                rightMax--;
            }
            final int tmp = nums[rightMin];
            nums[rightMin] = nums[rightMax];
            nums[rightMax] = tmp;
        }
        Arrays.sort(nums, rightMin + 1, len);
    }

}
