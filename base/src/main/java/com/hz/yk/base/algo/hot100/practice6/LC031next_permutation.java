package com.hz.yk.base.algo.hot100.practice6;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/next-permutation/description/
 * 整数数组的一个 排列  就是将其所有成员以序列或线性顺序排列。
 * 整数数组的 下一个排列 是指其整数的下一个字典序更大的排列。更正式地，如果数组的所有排列根据其字典顺序从小到大排列在一个容器中，那么数组的 下一个排列 就是在这个有序容器中排在它后面的那个排列。如果不存在下一个更大的排列，那么这个数组必须重排为字典序最小的排列（即，其元素按升序排列）。
 * 给你一个整数数组 nums ，找出 nums 的下一个排列。
 *
 * @author wuzheng.yk
 * @date 2024/2/15
 */
public class LC031next_permutation {

    /**
     * 123456
     * 123465
     * 654321
     * 151
     *
     * @param nums
     */
    public void nextPermutation(int[] nums) {
        if (nums.length < 2) {
            return;
        }
        int i = nums.length - 2, k = nums.length - 1;
        //从右边开始找到最小值，注意这里对等于的处理
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        //如果不是最大的那个数
        if (i >= 0) {
            //需要从右边开始找比nums[i] 大的最小数，因为i 右边是降序排列的，所以直接从最右边开始找
            // 注意这里的等于情况
            while (i < k && nums[k] <= nums[i]) {
                k--;
            }
            int tmp = nums[i];
            nums[i] = nums[k];
            nums[k] = tmp;
        }
        //对i后面的数字重新排序
        Arrays.sort(nums, i + 1, nums.length);
    }

    public static void main(String[] args) {
        LC031next_permutation test = new LC031next_permutation();
        test.nextPermutation(new int[]{ 1, 5, 1 });
    }
}
