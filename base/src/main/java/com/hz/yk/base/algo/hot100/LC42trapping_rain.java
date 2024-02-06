package com.hz.yk.base.algo.hot100;

import java.util.LinkedList;

/**
 * https://leetcode.cn/problems/trapping-rain-water/description/
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 *
 * @author wuzheng.yk
 * @date 2024/2/6
 */
public class LC42trapping_rain {

    /**
     * 使用左右夹逼法
     *
     * @param height
     * @return
     */
    public int trap(int[] height) {
        int result = 0;
        int left = 0, right = height.length - 1;
        int maxL = 0, maxR = 0;
        while (left < right) {
            //表示最左边遇到的最高的墙
            maxL = Math.max(maxL, height[left]);
            //表示最右边遇到的最高的墙
            maxR = Math.max(maxR, height[right]);
            //左边的墙矮，从左边往右靠近
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

    /**
     * 使用递减栈
     *
     * @param height
     * @return
     */
    public int trap1(int[] height) {
        int result = 0;
        LinkedList<Integer> stack = new LinkedList<>();
        for (int i = 0; i < height.length; i++) {
            //当栈不为空，而且当前元素大于栈顶元素，说明栈顶预算遇到了右边界，一直循环直到栈为空
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                int bottomIndex = stack.pop();
                //如果这个时候栈为空说明没有左边界，这个时候构成不了空间
                if (stack.isEmpty()) {
                    break;
                }
                //做边界下标
                int left = stack.peek();
                int h = Math.min(height[left], height[i]) - height[bottomIndex];
                result += h * (i - left - 1);
            }
            stack.push(i);
        }
        return result;
    }
}
