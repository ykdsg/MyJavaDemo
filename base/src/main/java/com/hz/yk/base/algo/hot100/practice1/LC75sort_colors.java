package com.hz.yk.base.algo.hot100.practice1;

/**
 * https://leetcode.cn/problems/sort-colors/description/
 * 给定一个包含红色、白色和蓝色、共 n 个元素的数组 nums ，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 * 我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 * 必须在不使用库内置的 sort 函数的情况下解决这个问题。
 *
 * @author wuzheng.yk
 * @date 2024/2/23
 */
public class LC75sort_colors {

    /**
     * 001002012
     * 120
     *
     * @param nums
     */
    public void sortColors(int[] nums) {
        int zero = 0, two = nums.length - 1;
        int cur = 0;
        //    [0,zero] =0
        //    (zero,cur]=1
        //    (two,len-1]=2

        //不能直接使用for 循环，特别是当前坐标跟数字2 下标交换之后还需要判断当前下标的值
        while (cur <= two) {
            // 注意这里必须使用else if ，不能直接使用if ，因为第一个条件已经对cur 变量进行变更
            if (nums[cur] == 0) {
                swap(nums, cur, zero);
                zero++;
                cur++;
            } else if (nums[cur] == 1) {
                cur++;
            } else {
                swap(nums, cur, two);
                two--;
                //注意这时候cur交换过来的值还要再判断一下，所以这里cur不动
            }
        }

    }

    private void swap(int[] nums, int a, int b) {
        final int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }
}
