package com.hz.yk.base.algo.hot100.practice1;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * https://leetcode.cn/problems/largest-rectangle-in-histogram/description/
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 *
 * @author wuzheng.yk
 * @date 2024/2/26
 */
public class LC84largest_rectangle {

    public int largestRectangleArea(int[] heights) {
        //单调递增栈
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(-1); //左哨兵下标
        int max = 0;
        for (int i = 0; i <= heights.length; i++) {
            while (getH(heights, i) < getH(heights, stack.peek())) {
                int height = heights[stack.pop()];
                int width = i - stack.peek() - 1;
                max = Math.max(max, height * width);
            }
            stack.push(i);
        }
        return max;

    }

    private int getH(int[] heights, int i) {
        if (i == -1 || i == heights.length) { //左右哨兵返回-1，表示最矮的柱子
            return -1;
        } else {
            return heights[i];
        }
    }

}
