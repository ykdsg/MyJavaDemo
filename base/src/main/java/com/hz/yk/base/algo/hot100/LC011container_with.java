package com.hz.yk.base.algo.hot100;

/**
 * https://leetcode.cn/problems/container-with-most-water/description/
 * 给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * 返回容器可以储存的最大水量。
 *
 * @author wuzheng.yk
 * @date 2024/1/24
 */
public class LC011container_with {

    public int maxArea0(int[] height) {
        int left = 0, right = height.length - 1;
        int max = 0;

        while (left < right) {
            int high = Math.min(height[left], height[right]);
            int wide = right - left;
            max = Math.max(max, high * wide);
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return max;
    }

    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int max = 0;
        while (left < right) {
            int high = Math.min(height[left], height[right]);
            int wide = right - left;
            max = Math.max(max, high * wide);
            if (height[left] <= height[right]) {
                left = getNextLeft(height, left, right);
            } else {
                right = getNextRight(height, left, right);
            }
        }
        return max;
    }

    private int getNextRight(int[] height, int left, int right) {
        int pre = right;
        int cur = right - 1;
        while (cur > left) {
            if (height[cur] > height[pre]) {
                return cur;
            } else {
                pre = cur;
                cur -= 1;
            }
        }
        return cur;
    }

    private int getNextLeft(int[] height, int left, int right) {
        int pre = left;
        int cur = left + 1;
        while (cur < right) {
            if (height[cur] > height[pre]) {
                return cur;
            } else {
                pre = cur;
                cur += 1;
            }
        }
        return cur;
    }
}
