package com.hz.yk.base.algo.hot100.practice3;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/next-permutation/description/
 * 整数数组的一个 排列  就是将其所有成员以序列或线性顺序排列。
 * 整数数组的 下一个排列 是指其整数的下一个字典序更大的排列。更正式地，如果数组的所有排列根据其字典顺序从小到大排列在一个容器中，那么数组的 下一个排列 就是在这个有序容器中排在它后面的那个排列。如果不存在下一个更大的排列，那么这个数组必须重排为字典序最小的排列（即，其元素按升序排列）。
 * 给你一个整数数组 nums ，找出 nums 的下一个排列。
 *
 * @author wuzheng.yk
 * @date 2024/1/30
 */
public class LC031next_permutation {

    public void nextPermutation(int[] nums) {
        final int len = nums.length;
        if (len < 2) {
            return;
        }
        int i = len - 2, k = len - 1;
        //找到最右边的小数
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        // 如果i=-1 ，说明这个数目前是最大的情况，直接重排位置即可
        if (i >= 0) {
            //找到最右边的大数
            while (nums[k] <= nums[i]) {
                k--;
            }
            //交换大数小数的位置
            int tmp = nums[i];
            nums[i] = nums[k];
            nums[k] = tmp;
        }
        // 重新排序换位置之后的数字
        Arrays.sort(nums, i + 1, len);
    }
}
