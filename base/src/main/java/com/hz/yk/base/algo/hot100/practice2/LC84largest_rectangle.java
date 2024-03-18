package com.hz.yk.base.algo.hot100.practice2;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * https://leetcode.cn/problems/largest-rectangle-in-histogram/description/
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 *
 * @author wuzheng.yk
 * @date 2024/3/17
 */
public class LC84largest_rectangle {

    public int largestRectangleArea(int[] heights) {
        //维护一个单调递增栈
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(-1); //放入左哨兵，能保证他始终在栈里面
        int max = 0;
        for (int i = 0; i <= heights.length; i++) { // 这里使用heights.length 作为右哨兵
            while (getH(heights, i) < getH(heights, stack.peek())) {
                final Integer pre = stack.pop();
                int area = (i - stack.peek() - 1) * getH(heights, pre);
                max = Math.max(max, area);
            }
            stack.push(i);
        }
        return max;
    }

    private int getH(int[] heights, int index) {
        if (index == -1 || index == heights.length) {
            return -1;
        }
        return heights[index];
    }

}
