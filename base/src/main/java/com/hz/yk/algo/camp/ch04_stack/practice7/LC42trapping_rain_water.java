package com.hz.yk.algo.camp.ch04_stack.practice7;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * https://leetcode.cn/problems/trapping-rain-water/
 *
 * @author wuzheng.yk
 * @date 2023/7/28
 */
public class LC42trapping_rain_water {

    public int trap(int[] height) {
        Deque<Integer> stack = new ArrayDeque();
        int area = 0;
        for (int i = 0; i < height.length; i++) {
            //遇到右边界的情况
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                final Integer cur = stack.pop();
                final Integer left = stack.peek();
                //没有左边界了
                if (left == null) {
                    break;
                }
                final int h = Math.min(height[left], height[i]) - height[cur];
                area += h * (i - left - 1);
            }
            stack.push(i);
        }
        return area;
    }

    public static void main(String[] args) {
        LC42trapping_rain_water test = new LC42trapping_rain_water();
        final int result = test.trap(new int[]{ 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 });
        System.out.println(result);

    }
}
