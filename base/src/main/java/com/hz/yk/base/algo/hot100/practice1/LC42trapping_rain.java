package com.hz.yk.base.algo.hot100.practice1;

import java.util.LinkedList;

/**
 * https://leetcode.cn/problems/trapping-rain-water/description/
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 *
 * @author wuzheng.yk
 * @date 2024/2/7
 */
public class LC42trapping_rain {

    public int trap(int[] height) {
        int left = 0, right = height.length - 1;
        int maxL = 0, maxR = 0;
        int result = 0;
        while (left < right) {
            maxL = Math.max(maxL, height[left]);
            maxR = Math.max(maxR, height[right]);
            // 比较大小，选择哪一边的墙进行判断
            if (maxL < maxR) {
                result += maxL - height[left];
                left++;
            } else {
                result += maxR - height[right];
                right--;
            }
        }
        return result;
    }

    /**
     * 用单调栈实现
     *
     * @param height
     * @return
     */
    public int trap2(int[] height) {
        LinkedList<Integer> stack = new LinkedList<>();
        int result = 0;
        for (int i = 0; i < height.length; i++) {
            // 如果当前值大于栈顶元素，说明遇到了右边界
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                int cur = stack.pop();
                //如果此时栈为空，说明没有左边界
                if (stack.isEmpty()) {
                    break;
                }
                final Integer left = stack.peek();
                int h = Math.min(height[left], height[i]) - height[cur];
                //注意这里的-1
                result += (i - left - 1) * h;
            }
            stack.push(i);
        }
        return result;
    }

}
