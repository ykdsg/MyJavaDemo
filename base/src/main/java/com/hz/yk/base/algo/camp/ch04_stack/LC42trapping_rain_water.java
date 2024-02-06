package com.hz.yk.base.algo.camp.ch04_stack;

import java.util.LinkedList;

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
