package com.hz.yk.algo.camp.ch04_stack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 *
 * @author wuzheng.yk
 * @date 2023/7/4
 */
public class LC84largest_rectangle_in_histogram {

    /**
     * 出栈决定右边界，入栈决定左边界
     * @param heights
     * @return
     */
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] left = new int[n];
        int[] right = new int[n];
        Arrays.fill(right, n);

        Deque<Integer> stack = new ArrayDeque<Integer>();
        for (int i = 0; i < n; ++i) {
            //如果遇到比自己长度小的，就可以确定右边界
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                right[stack.peek()] = i;
                stack.pop();
            }
            // 如果可以放入stack，这个时候也可以确定左边界，因为栈顶的元素也比当前值小
            left[i] = (stack.isEmpty() ? -1 : stack.peek());
            stack.push(i);
        }

        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans = Math.max(ans, (right[i] - left[i] - 1) * heights[i]);
        }
        return ans;
    }

    /**
     * 这个思想跟上面的一致，只是在出栈的时候直接计算
     * @param heights
     * @return
     */
    public int largestRectangleArea2(int[] heights) {
        Deque<Integer> stack = new ArrayDeque<Integer>();
        stack.push(-1);
        int maxarea = 0;
        for (int i = 0; i < heights.length; i++) {
            while (stack.peek() != -1 && heights[stack.peek()] >= heights[i]) {
                //说明栈顶元素找到了右边界
                final int height = heights[stack.pop()];
                //此时栈顶元素的左边界就是紧挨着他的下一个栈，那么宽度就是i-left
                int left = stack.peek() == -1 ? 0 : (stack.peek()+1);
                maxarea = Math.max(maxarea, height * (i - left));
            }
            stack.push(i);
        }
        //如果这个时候栈中还有元素，说明栈中元素的右边界就是heights 数组最右侧
        while (stack.peek() != -1) {
            final int height = heights[stack.pop()];
            int left = stack.peek() == -1 ? 0 : (stack.peek()+1);
            maxarea = Math.max(maxarea, height * (heights.length - left));
        }
        return maxarea;
    }

    public int largestRectangleArea3(int[] heights) {
        int len = heights.length;
        int[] newHeights = new int[len + 2];
        //在起始位置放入0作为哨兵，是为了跟stack 的初始0相对应，相当于一个占位符
        newHeights[0] = 0;
        System.arraycopy(heights, 0, newHeights, 1, len);
        //最后一个位置放入0，是为了能够在一个循环里就把栈清空，避免了上个case 里面还要处理栈不为空的情况
        newHeights[len + 1] = 0;
        len += 2;

        int maxarea = 0;
        
        Deque<Integer> stack = new ArrayDeque<>();
        //stack 初始化0 是为了不用判空
        stack.push(0);
        for (int i = 1; i < len; i++) {
            while (newHeights[i] < newHeights[stack.peek()]) {
                int hight = newHeights[stack.pop()];
                int width = i - stack.peek() - 1;
                maxarea = Math.max(maxarea, hight * width);
            }
            stack.push(i);
        }
        return maxarea;
    }
    
}
