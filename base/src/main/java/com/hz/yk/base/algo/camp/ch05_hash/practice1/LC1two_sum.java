package com.hz.yk.base.algo.camp.ch05_hash.practice1;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
 * https://leetcode.cn/problems/two-sum/
 *
 * @author wuzheng.yk
 * @date 2023/7/20
 */
public class LC1two_sum {

    public int[] twoSum1(int[] nums, int target) {
        Map<Integer, Integer> cache = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            final int var = nums[i];
            final int nextV = target - var;
            if (cache.containsKey(nextV)) {
                return new int[]{ i, cache.get(nextV) };
            } else {
                cache.put(var, i);
            }
        }
        return new int[0];
    }

}
