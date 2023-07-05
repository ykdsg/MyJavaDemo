package com.hz.yk.algo.camp.ch04_list;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 给你一个整数数组 nums，有一个大小为k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 * 返回 滑动窗口中的最大值 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/sliding-window-maximum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author wuzheng.yk
 * @date 2023/7/5
 */
public class LC239sliding_window_maximum {
    public int[] maxSlidingWindow(int[] nums, int k) {
        //结果个数
        int[] result = new int[nums.length - k + 1];
        Deque<Integer> queue = new LinkedList<>();
        for (int right = 0; right < nums.length; right++) {
            //如果队列尾部比当前值小，可以直接丢弃
            while (!queue.isEmpty() && nums[right] >= nums[queue.peekLast()]) {
                queue.removeLast();
            }
            queue.add(right);
            //存储下标的左边界
            int left = right - k + 1;
            if (queue.peekFirst() < left) {
                queue.removeFirst();
            }

            if (right + 1 >= k) {
                result[left] = nums[queue.peekFirst()];
            }
        }
        return result;

    }
}
