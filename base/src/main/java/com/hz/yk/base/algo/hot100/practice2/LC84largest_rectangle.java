package com.hz.yk.base.algo.hot100.practice2;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * https://leetcode.cn/problems/largest-rectangle-in-histogram/description/
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 *
 * @author wuzheng.yk
 * @date 2024/2/29
 */
public class LC84largest_rectangle {

    public int largestRectangleArea(int[] heights) {
        //单调递增栈
        Deque<Integer> stack = new ArrayDeque<>();
        //增加哨兵机制
        stack.push(-1);
        // 这里增加右哨兵 heights.length
        int max = 0;
        for (int i = 0; i <= heights.length; i++) {
            while (getH(heights, i) < getH(heights, stack.peek())) {
                final int height = getH(heights, stack.pop());
                final int width = i - stack.peek() - 1;
                max = Math.max(max, height * width);
            }
            stack.push(i);
        }

        return max;
    }

    private int getH(int[] heights, Integer i) {
        if (i == -1 || i == heights.length) {
            return -1;
        } else {
            return heights[i];
        }
    }
}
