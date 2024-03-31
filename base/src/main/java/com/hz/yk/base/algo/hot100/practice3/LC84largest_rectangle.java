package com.hz.yk.base.algo.hot100.practice3;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * https://leetcode.cn/problems/largest-rectangle-in-histogram/description/
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 *
 * @author wuzheng.yk
 * @date 2024/3/31
 */
public class LC84largest_rectangle {

    public int largestRectangleArea(int[] heights) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(-1); //左哨兵
        int max = 0;
        for (int i = 0; i <= heights.length; i++) {
            while (getHeight(heights, i) < getHeight(heights, stack.peek())) {
                final int h = heights[stack.pop()];
                final int w = i - stack.peek() - 1;
                max = Math.max(max, h * w);
            }
            stack.push(i);
        }
        return max;

    }

    private int getHeight(int[] heights, int i) {
        if (i == -1 || i == heights.length) {
            return -1;//左右哨兵返回-1
        }
        return heights[i];
    }

}
