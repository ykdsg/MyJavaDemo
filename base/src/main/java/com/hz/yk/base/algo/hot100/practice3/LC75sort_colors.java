package com.hz.yk.base.algo.hot100.practice3;

/**
 * https://leetcode.cn/problems/sort-colors/description/
 * 给定一个包含红色、白色和蓝色、共 n 个元素的数组 nums ，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 * 我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 * 必须在不使用库内置的 sort 函数的情况下解决这个问题。
 *
 * @author wuzheng.yk
 * @date 2024/2/29
 */
public class LC75sort_colors {

    public void sortColors(int[] nums) {
        int zero = 0, cur = 0, two = nums.length - 1;
        //[0,1)=0
        // [1,2)=1
        //[2,end]=2

        //需要注意这里是=，否则cur这个位置刚好是0的时候会存在问题
        while (cur <= two) {
            if (nums[cur] == 0) {
                swap(nums, cur, zero);
                zero++;
                cur++;
            } else if (nums[cur] == 1) {
                cur++;
            } else {
                swap(nums, cur, two);
                two--;
            }
        }
    }

    private void swap(int[] nums, int a, int b) {
        final int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }

}
