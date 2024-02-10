package com.hz.yk.base.algo.hot100.practice2;

/**
 * https://leetcode.cn/problems/trapping-rain-water/description/
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 *
 * @author wuzheng.yk
 * @date 2024/2/8
 */
public class LC42trapping_rain {

    public int trap(int[] height) {
        if (height.length < 3) {
            return 0;
        }
        int left = 0, right = height.length - 1;
        int maxLeft = 0, maxRight = 0;
        int result = 0;
        while (left < right) {
            maxLeft = Math.max(maxLeft, height[left]);
            maxRight = Math.max(maxRight, height[right]);
            if (maxLeft < maxRight) {
                result += maxLeft - height[left];
                left++;
            } else {
                result += maxRight - height[right];
                right--;
            }
        }
        return result;
    }
}
