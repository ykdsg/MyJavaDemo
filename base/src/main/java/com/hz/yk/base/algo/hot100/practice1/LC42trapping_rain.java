package com.hz.yk.base.algo.hot100.practice1;

/**
 * https://leetcode.cn/problems/trapping-rain-water/description/
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 *
 * @author wuzheng.yk
 * @date 2024/3/23
 */
public class LC42trapping_rain {

    public int trap(int[] height) {
        int result = 0;
        int left = 0, right = height.length - 1;
        int maxL = 0, maxR = 0;
        while (left < right) {
            maxL = Math.max(maxL, height[left]);
            maxR = Math.max(maxR, height[right]);
            if (maxL < maxR) {
                result += maxL - height[left];
                left++;
            } else {
                result += maxR - height[right];
                right--;
            }
        }
        return result;
    }

}
