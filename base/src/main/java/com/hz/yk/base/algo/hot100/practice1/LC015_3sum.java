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
 * @date 2024/3/18
 */
public class LC015_3sum {

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i - 1] == nums[i]) {//避免重复的情况
                continue;
            }
            if (nums[i] > 0) { //对于排序过的数组当前已经大于0，往后面的数字相加肯定不为0
                break;
            }
            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                final int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    left = getNextLeft(nums, left, right);
                    right = getNextRight(nums, left, right);
                } else if (sum < 0) {
                    left = getNextLeft(nums, left, right);
                } else {
                    right = getNextRight(nums, left, right);
                }
            }
        }
        return result;
    }

    private int getNextRight(int[] nums, int left, int right) {
        right--;
        while (left <= right && nums[right + 1] == nums[right]) {
            right--;
        }
        return right;
    }

    private int getNextLeft(int[] nums, int left, int right) {
        left += 1;
        while (left <= right && nums[left - 1] == nums[left]) {
            left++;
        }
        return left;
    }

}
