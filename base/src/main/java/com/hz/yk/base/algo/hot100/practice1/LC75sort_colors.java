package com.hz.yk.base.algo.hot100.practice1;

/**
 * https://leetcode.cn/problems/sort-colors/description/
 * 给定一个包含红色、白色和蓝色、共 n 个元素的数组 nums ，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 * 我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 * 必须在不使用库内置的 sort 函数的情况下解决这个问题。
 *
 * @author wuzheng.yk
 * @date 2024/3/12
 */
public class LC75sort_colors {

    /**
     * 101201
     * 210
     *
     * @param nums
     */
    public void sortColors(int[] nums) {
        if (nums.length == 0) {
            return;
        }
        //定义几个下标，需要实现
        // all in [0, zero] = 0
        // all in (zero, i) = 1
        // all in (two, len - 1] = 2
        int zero = 0, cur = 0, two = nums.length - 1;
        while (cur <= two) {
            if (nums[cur] == 0) {
                swap(nums, zero, cur);
                zero++;
                cur++;
            } else if (nums[cur] == 1) {
                cur++;
            } else {
                swap(nums, cur, two);
                //注意 这个时候cur 不能移动，因为交换之后还要再判断下
                two--;
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        final int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
