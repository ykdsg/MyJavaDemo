package com.hz.yk.algo.camp.ch04_stack.practice1;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 * 返回 滑动窗口中的最大值 。
 * https://leetcode.cn/problems/sliding-window-maximum/
 *
 * @author wuzheng.yk
 * @date 2023/7/19
 */
public class LC239sliding_window_maximum {

    public static int[] maxSlidingWindow(int[] nums, int k) {
        int[] result = new int[nums.length - k + 1];
        //维护一个单调递减队列
        Deque<Integer> queue = new ArrayDeque<>();
        queue.add(nums[0]);

        for (int i = 0; i < nums.length; i++) {
            //如果当前游标值大于队列尾元素，那应该删掉队尾元素，直到遇到比自己大的
            while (!queue.isEmpty() && nums[i] >= nums[queue.peekLast()]) {
                queue.removeLast();
            }
            //这个时候加入队列，队列还是单调递减的。
            queue.add(i);

            //滑动窗口左边界
            int left = i - k + 1;
            if (left >= 0) {
                //如果窗口左边界索引大于队列头元素，说明队列头元素这个时候不在窗口内
                if (left > queue.getFirst()) {
                    queue.removeFirst();
                }
                result[left] = nums[queue.peekFirst()];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{ 1,3,1,2,0,5 };
        int k = 3;
        final int[] result = maxSlidingWindow(nums, k);
        //应该是3325
        System.out.println(Arrays.toString(result));
    }
}
