package com.hz.yk.algo.camp.ch08_backtracking;

import java.util.Arrays;

/**
 * 给定一个大小为 n 的数组 nums ，返回其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
 * https://leetcode.cn/problems/majority-element/
 *
 * @author wuzheng.yk
 * @date 2023/7/25
 */
public class LC169majority_element {

    //根据定义，排序之后的中间值就是众数
    //时间复杂度：nlogn
    public int majorityElement1(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    //使用分治方法
    public int majorityElement2(int[] nums) {
        return dsf(nums, 0, nums.length - 1);
    }

    private int dsf(int[] nums, int lo, int hi) {
        if (lo == hi) {
            return nums[lo];
        }
        final int mid = (hi - lo) / 2+lo;
        final int left = dsf(nums, lo, mid);
        final int right = dsf(nums, mid + 1, hi);

        if (left == right) {
            return left;
        }
        final int leftCount = countValue(nums, left, lo, mid);
        final int rightCount = countValue(nums, right, mid + 1, hi);

        return leftCount > rightCount ? left : right;
    }

    private int countValue(int[] nums, int num, int lo, int hi) {
        int count = 0;
        for (int i = lo; i <= hi; i++) {
            if (nums[i] == num) {
                count++;
            }
        }
        return count;
    }

}
