package com.hz.yk.base.algo.hot100;

/**
 * https://leetcode.cn/problems/sort-colors/description/
 * 给定一个包含红色、白色和蓝色、共 n 个元素的数组 nums ，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 * 我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 * 必须在不使用库内置的 sort 函数的情况下解决这个问题。
 *
 * @author wuzheng.yk
 * @date 2024/2/21
 */
public class LC75sort_colors {

    /**
     * 001012112
     *
     * @param nums
     */
    public void sortColors(int[] nums) {
        final int len = nums.length;
        if (len < 2) {
            return;
        }
        //定义几个下标，需要实现
        // all in [0, zero] = 0
        // all in (zero, i) = 1
        // all in (two, len - 1] = 2

        int zero = 0, i = 0, two = len - 1;
        // 当 i == two 上面的三个子区间正好覆盖了全部数组
        // 因此，循环可以继续的条件是 i <= two
        while (i <= two) {
            if (nums[i] == 0) {
                swap(nums, i, zero);
                zero++;
                i++;
            } else if (nums[i] == 1) {
                i++;
            } else {
                // 这里相当于two指针在靠近中间了，所以i指针此时不需要动
                swap(nums, i, two);
                two--;
            }
        }

    }

    private void swap(int[] nums, int a, int b) {
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }
}
