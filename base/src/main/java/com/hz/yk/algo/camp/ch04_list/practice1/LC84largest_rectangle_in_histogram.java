package com.hz.yk.algo.camp.ch04_list.practice1;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 * https://leetcode.cn/problems/largest-rectangle-in-histogram/
 * @author wuzheng.yk
 * @date 2023/7/18
 */
public class LC84largest_rectangle_in_histogram {

    public int largestRectangleArea(int[] heights) {
        final int n = heights.length;
        int[] left = new int[n];
        int[] right = new int[n];

        Arrays.fill(right, n);
        // 整体思路是维护一个单调递增栈
        Deque<Integer> stack = new ArrayDeque<Integer>();
        
        //当前元素小于栈顶元素的时候，说明找到了右边界
        for (int i = 0; i < n; i++) {
            //如果栈不为空，且栈顶的高度大于等于当前值，说明可以确定右边界
            while (!stack.isEmpty() && heights[stack.peek()] > heights[i]) {
                right[stack.peek()] = i;
                stack.pop();
            }
            //如果可以入栈，说明该当前的栈顶元素是当前坐标的左边界，因为是单调递增栈
            left[i] = stack.isEmpty()?-1:stack.peek();
            stack.push(i);
        }
        int max = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, (right[i] - left[i] - 1) * heights[i]);
        }
        return max;
    }


    //整体思路跟上面一致，只是在遇到右边界的时候直接进行面积的计算，因为左边界就是下一个栈顶元素。
    public int largestRectangleArea2(int[] heights) {
        Deque<Integer> stack = new ArrayDeque<Integer>();
        stack.push(-1);
        int max = 0;
        for (int i = 0; i < heights.length; i++) {
            while (stack.peek() != -1 && heights[stack.peek()] > heights[i]) {
                final int height = heights[stack.pop()];
                max = Math.max(max, (i - stack.peek() - 1) * height);
            }
            stack.push(i);
        }
        //如果栈中还有元素，说明右边界就是heights数组的最右侧
        while (stack.peek() != -1) {
            final int height = heights[stack.pop()];
            int left = stack.peek();
            max = Math.max(max, height * (heights.length - left - 1));
        }
        return max;
    }


    public int largestRectangleArea3(int[] heights) {
        int len = heights.length;
        int[] newHeights = new int[len + 2];
        System.arraycopy(heights, 0, newHeights, 1, len);
        //在头尾设置哨兵
        newHeights[0] = 0;
        newHeights[len + 1] = 0;

        int max = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        //因为设置头哨兵的缘故，这里可以直接放入0，所以for 循环要从1开始
        stack.push(0);
        for (int i = 1; i < newHeights.length; i++) {
            //尾部的0保证了会把前面的棍子都踢出栈
            while (newHeights[stack.peek()] > newHeights[i]) {
                final int height = newHeights[stack.pop()];
                max = Math.max(max, height * (i - stack.peek()-1));
            }
            //循环到最后会放入最后一个0，这个时候已经不用再计算了
            stack.push(i);
        }
        return max;
    }
    
    

    public static void main(String[] args) {
        LC84largest_rectangle_in_histogram test = new LC84largest_rectangle_in_histogram();
        final int area = test.largestRectangleArea2(new int[]{ 2, 1, 5, 6, 2, 3 });
        System.out.println(area);
    }
}
