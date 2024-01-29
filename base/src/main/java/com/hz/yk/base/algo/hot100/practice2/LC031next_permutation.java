package com.hz.yk.base.algo.hot100.practice2;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/next-permutation/description/
 * 整数数组的一个 排列  就是将其所有成员以序列或线性顺序排列。
 * 整数数组的 下一个排列 是指其整数的下一个字典序更大的排列。更正式地，如果数组的所有排列根据其字典顺序从小到大排列在一个容器中，那么数组的 下一个排列 就是在这个有序容器中排在它后面的那个排列。如果不存在下一个更大的排列，那么这个数组必须重排为字典序最小的排列（即，其元素按升序排列）。
 * 给你一个整数数组 nums ，找出 nums 的下一个排列。
 *
 * @author wuzheng.yk
 * @date 2024/1/29
 */
public class LC031next_permutation {

    public void nextPermutation(int[] nums) {
        final int len = nums.length;
        if (len < 2) {
            return;
        }
        int i = len - 2, k = len - 1;
        //找到第一个小数，注意这里是>=
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        if (i >= 0) { // 不是最后一种情况（最大值）
            //从最右边开始找最小的大数
            while (nums[k] <= nums[i]) {
                k--;
            }
            //交互小数和大数的位置
            final int tmp = nums[i];
            nums[i] = nums[k];
            nums[k] = tmp;
        }
        //注意排序是在i+1 开始，不是从i开始
        Arrays.sort(nums, i + 1, len);
    }

    public static void main(String[] args) {
        LC031next_permutation test = new LC031next_permutation();
        final int[] nums = { 1, 2, 3 };
        test.nextPermutation(nums);
        System.out.println(nums);

    }

}
