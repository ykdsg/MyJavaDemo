package com.hz.yk.base.algo.hot100;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/next-permutation/description/
 * 整数数组的一个 排列  就是将其所有成员以序列或线性顺序排列。
 * 整数数组的 下一个排列 是指其整数的下一个字典序更大的排列。更正式地，如果数组的所有排列根据其字典顺序从小到大排列在一个容器中，那么数组的 下一个排列 就是在这个有序容器中排在它后面的那个排列。如果不存在下一个更大的排列，那么这个数组必须重排为字典序最小的排列（即，其元素按升序排列）。
 * 给你一个整数数组 nums ，找出 nums 的下一个排列。
 *
 * @author wuzheng.yk
 * @date 2024/1/27
 */
public class LC031next_permutation {

    /**
     * 123456
     * 123465
     * 123546
     *
     * @param nums
     */
    public void nextPermutation(int[] nums) {
        final int len = nums.length;
        if (len < 2) {
            return;
        }
        int i = len - 2, k = len - 1;
        //找到最右边的“小数”
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        //如果不是最后一种情况，注意这里包含=0 的情况
        if (i >= 0) {
            //从后往前，找到尽可能“小”的大数
            while (nums[i] >= nums[k]) {
                k--;
            }
            int tmp = nums[i];
            nums[i] = nums[k];
            nums[k] = tmp;
        }
        //需要将i+1 之后的数字排序
        //将「大数」换到前面后，需要将「大数」后面的所有数 重置为升序，升序排列就是最小的排列。
        Arrays.sort(nums, i + 1, len);
    }
}
