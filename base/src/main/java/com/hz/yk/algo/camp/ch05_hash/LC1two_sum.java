package com.hz.yk.algo.camp.ch05_hash;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个整数数组 nums和一个整数目标值 target，请你在该数组中找出 和为目标值 target的那两个整数，并返回它们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 *
 * @author wuzheng.yk
 * @date 2023/6/28
 */
public class LC1two_sum {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> numIndexMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            final int leftValue = target - nums[i];
            if (numIndexMap.containsKey(leftValue)) {
                return new int[]{ numIndexMap.get(leftValue), i };
            } else {
                numIndexMap.put(nums[i], i);
            } 
        }
        return new int[]{};

    }

    public static void main(String[] args) {
        LC1two_sum test = new LC1two_sum();
        final int[] result = test.twoSum(new int[]{ 2, 7, 11, 15 }, 9);
            
    }
    
}
