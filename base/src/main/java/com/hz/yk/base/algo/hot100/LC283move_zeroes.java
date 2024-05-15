package com.hz.yk.base.algo.hot100;

/**
 * https://leetcode.cn/problems/move-zeroes/description/?envType=study-plan-v2&envId=top-100-liked
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * 请注意 ，必须在不复制数组的情况下原地对数组进行操作。
 *
 * @author wuzheng.yk
 * @date 2024/5/9
 */
public class LC283move_zeroes {

    /**
     * 注意题目还要求保持相对顺序，这里通过维持0的下标进行移动
     *
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        int zero = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                if (i != zero) {
                    int tmp = nums[i];
                    nums[i] = nums[zero];
                    nums[zero] = tmp;
                }
                zero++;
            }
        }
    }
}
