package com.hz.yk.algo.camp.ch03_array;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * 返回容器可以储存的最大水量。
 * https://leetcode.cn/problems/container-with-most-water/
 *
 * @author wuzheng.yk
 * @date 2023/7/31
 */
public class LC11container_with_most_water {

    public int maxArea(int[] height) {
        int max = 0;
        int left = 0, right = height.length - 1;
        while (left < right) {
            int h = Math.min(height[left], height[right]);
            max = Math.max(max, h * (right - left));
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            } 
        }
        return max;

    }

    @Test
    public void test(){
        LC11container_with_most_water test = new LC11container_with_most_water();
        int[] result_49 = new int[]{ 1, 8, 6, 2, 5, 4, 8, 3, 7 };
        final int result = test.maxArea(result_49);
        assertEquals(49, result);

        final int[] result_1 = { 1, 1 };
        assertEquals(1, test.maxArea(result_1));

    }
}
