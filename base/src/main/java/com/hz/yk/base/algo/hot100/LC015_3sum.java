package com.hz.yk.base.algo.hot100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.cn/problems/3sum/description/
 * 给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，同时还满足 nums[i] + nums[j] + nums[k] == 0 。请
 * 你返回所有和为 0 且不重复的三元组。
 * 注意：答案中不可以包含重复的三元组。
 *
 * @author wuzheng.yk
 * @date 2024/1/24
 */
public class LC015_3sum {

    /**
     * 使用左右夹逼方法，需要提前排序
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums.length < 3) {
            return null;
        }
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i] > 0) {
                return result;
            }

            //如果跟前一位相同说明已经遍历过同样的数值，可以直接跳过
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                final int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    left = findNextLeft(nums, left, right);
                    right = findNextRight(nums, left, right);
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return result;
    }

    private int findNextLeft(int[] nums, int left, int right) {
        while (left < right && nums[left] == nums[left + 1]) {
            left++;
        }
        return left + 1;
    }

    private int findNextRight(int[] nums, int left, int right) {
        while (left < right && nums[right] == nums[right - 1]) {
            right--;
        }
        return right - 1;
    }

    public static void main(String[] args) {
        LC015_3sum test = new LC015_3sum();
        int[] arrays = new int[]{ -1, 0, 1, 2, -1, -4 };
        test.threeSum(arrays);
    }
}
