package com.hz.yk.algo.camp.ch04_stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * https://leetcode.cn/problems/trapping-rain-water/
 * @author wuzheng.yk
 * @date 2023/7/5
 */
public class LC42trapping_rain_water {

    /**
     * 找到左右两边墙的高度
     * @param height
     * @return
     */
    public int trap1(int[] height) {
        int result = 0;
        for (int i =1; i < height.length - 1; i++) {
            int maxLeft = 0;

            //找到左边最长的值
            for (int j = i - 1; j >= 0; j--) {
                if (height[j] > maxLeft) {
                    maxLeft = height[j];
                }
            }
            int maxRight = 0;
            for (int j = i + 1; j < height.length; j++) {
                if (height[j] > maxRight) {
                    maxRight = height[j];
                }
            }
            final int min = Math.min(maxLeft, maxRight);
            // 只有墙都大于当前的柱子，才有空间留下雨水
            if (min > height[i]) {
                result += min - height[i];
            }
        }
        return result;
    }

    /**
     * 上面这种方法存在重复计算的问题，可以使用动态规划的方法降低重复计算
     * @param height
     * @return
     */
    public int trap2(int[] height) {
        int result = 0;
        int[] max_left = new int[height.length];
        int[] max_right = new int[height.length];

        for (int i = 1; i < height.length; i++) {
            max_left[i] = Math.max(max_left[i - 1], height[i - 1]);
        }
        for (int i = height.length - 2; i >= 0; i--) {
            max_right[i] = Math.max(max_right[i + 1], height[i + 1]);
        }
        //剩下就跟上面的类似
        for (int i = 1; i < height.length - 1; i++) {
            int min = Math.min(max_left[i], max_right[i]);
            if (min > height[i]) {
                result += min - height[i];
            }
        }
        return result;
    }

    /**
     * 使用栈实现，略微有点复杂，一旦理解就比较自然
     * 维护一个单调递减栈，如果当前值比栈顶元素大，说明遇到了右边界，那么左边界就是栈顶元素的下一个元素
     * 
     * @param height
     * @return
     */
    public int trap3(int[] height) {
        int result = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 1; i < height.length; i++) {
            //如果栈不为空，且当前指针的高度大于栈顶高度，能确定右边的墙
            while (!stack.isEmpty() && height[stack.peek()] < height[i]) {
                final Integer cur = stack.peek();
                stack.pop();
                //说明左边的墙没有，这样也接不了水
                if (stack.isEmpty()) {
                    break;
                }
                final Integer left = stack.peek();
                final int min = Math.min((height[i]), height[left]);
                int h = min - height[cur];
                result += h * (i - left);
            }
            //如果栈中没有小于当前柱子的高度，说明需要确定新的左边的墙
            stack.push(i);
        }
        return result;
    }
    
}
