package com.hz.yk.algo.camp.ch04_list.practice1;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * https://leetcode.cn/problems/trapping-rain-water/?utm_source=LCUS&utm_medium=ip_redirect&utm_campaign=transfer2china
 * 
 * @author wuzheng.yk
 * @date 2023/7/19
 */
public class LC42trapping_rain_water {
    public int trap1(int[] height) {
        int sum = 0;
        //维护了一个单调递减栈
        Deque<Integer> stack = new ArrayDeque();
        for (int i = 0; i < height.length; i++) {
            //当前值大于栈顶元素说明遇到右边界了
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                int cur = stack.pop();
                if (stack.isEmpty()) {
                    break;
                }
                int left = stack.peek();
                //高度由最短的边界 - 当前板子的长度
                int h = Math.min(height[left], height[i]) - height[cur];
                int w = i - left - 1;
                sum += h * w;
            }
            stack.push(i);
        }
        return sum;
    }

    /**
     * 这种方式更容易理解，先从左往右扫描，只要最大左边界大于当前值就记录可能的储水值。
     * 再从右往左扫描，知道最大右边界大于当前值就记录可能的储水值。
     * 然后这2部分数据交叉重叠部分（两边的最小值）就是实际可以存放的水。
     * @param height
     * @return
     */
    public int trap2(int[] height) {
        //起码要有3根柱子才能接雨水
        if (height.length <= 2) {
            return 0;
        }
        int[] lefts = new int[height.length];
        //从左边扫描，只要左边界能够高于当前柱子，说明就有可能盛水
        int maxLeft = height[0];
        for (int i = 1; i < height.length; i++) {
            lefts[i] = maxLeft < height[i] ? 0 : maxLeft - height[i];
            maxLeft = Math.max(maxLeft, height[i]);
        }
        int[] rights = new int[height.length];
        int maxRight = height[height.length - 1];
        for (int i = height.length-2; i > 0; i--) {
            rights[i] = maxRight < height[i] ? 0 : maxRight - height[i];
            maxRight = Math.max(maxRight, height[i]);
        }
        int sum = 0;
        for (int i = 0; i < height.length; i++) {
            sum += Math.min(lefts[i], rights[i]);
        }
        return sum;
    }
    
        


    public static void main(String[] args) {
        int[] height = new int[]{ 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 };
        LC42trapping_rain_water test = new LC42trapping_rain_water();
        final int result = test.trap1(height);
        System.out.println(result);

        System.out.println(test.trap2(height));
    }
}
