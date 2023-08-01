package com.hz.yk.algo.camp.ch03_array;

import java.util.Arrays;

/**
 * @author wuzheng.yk
 * @date 2023/8/1
 */
public class LC189rotate_array {

    public void rotate(int[] nums, int k) {
        final int n = nums.length;
        final int x = k % n;
        // 说明不用轮转
        if (x == 0) {
            return;
        }
        int start = 0;
        int startValue = nums[0];
        for (int i = 0; i < nums.length; i++) {
            final int end = (start + x) % n;
            final int tmp = nums[end];
            nums[end] = startValue;
            start = end;
            startValue = tmp;
        }
    }

    public static void main(String[] args) {
        LC189rotate_array test = new LC189rotate_array();
        final int[] nums = { 1, 2, 3, 4, 5, 6, 7 };
        test.rotate(nums, 3);
        System.out.println(Arrays.toString(nums));
    }
}
