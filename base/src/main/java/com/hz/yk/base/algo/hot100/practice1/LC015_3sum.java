package com.hz.yk.base.algo.hot100.practice1;

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

    public List<List<Integer>> threeSum(int[] nums) {
        if (nums.length < 3) {
            return null;
        }
        Arrays.sort(nums);

        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                return result;
            }
            //相同数字跳过
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int left = i + 1, right = nums.length - 1;
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

    //注意最后return 的时候 -1
    private int findNextRight(int[] nums, int left, int right) {
        while (left < right && nums[right] == nums[right - 1]) {
            right--;
        }
        return right - 1;
    }

    //注意最后return 的时候+1
    private int findNextLeft(int[] nums, int left, int right) {
        while (left < right && nums[left] == nums[left + 1]) {
            left++;
        }
        return left + 1;
    }
}
