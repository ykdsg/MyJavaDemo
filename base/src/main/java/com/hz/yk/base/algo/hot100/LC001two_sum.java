package com.hz.yk.base.algo.hot100;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.cn/problems/two-sum/description/
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 *
 * @author wuzheng.yk
 * @date 2024/1/20
 */
public class LC001two_sum {

    /**
     * 利用map的特性，关键是只用一遍for循环
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> cache = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int value = target - nums[i];
            if (cache.containsKey(value)) {
                return new int[]{ i, cache.get(value) };
            }
            cache.put(nums[i], i);
        }
        return null;

    }

}
