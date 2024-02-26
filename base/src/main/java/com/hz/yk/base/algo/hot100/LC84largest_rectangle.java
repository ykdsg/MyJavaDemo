package com.hz.yk.base.algo.hot100;

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

    /**
     * 使用单调递减栈，通过增加左右2个哨兵降低要处理栈不为空的情况
     *
     * @param heights
     * @return
     */
    public int largestRectangleArea(int[] heights) {
        final int len = heights.length;
        int max = 0;
        // 单调递增栈
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(-1); //左哨兵下标
        for (int i = 0; i <= len; i++) { //右哨兵下标为len
            while (getHeight(i, heights) < getHeight(stack.peek(), heights)) {
                int height = heights[stack.pop()];
                final int width = i - stack.peek() - 1;
                max = Math.max(max, height * width);
            }
            stack.push(i);
        }
        return max;
    }

    private int getHeight(int i, int[] heights) {
        if (i == -1) {
            return -1;
        } else if (i == heights.length) { // 右哨兵
            return -1;
        } else {
            return heights[i];
        }
    }
}
