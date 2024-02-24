package com.hz.yk.base.algo.hot100.practice2;

/**
 * https://leetcode.cn/problems/jump-game/description/
 * 给你一个非负整数数组 nums ，你最初位于数组的 第一个下标 。数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 判断你是否能够到达最后一个下标，如果可以，返回 true ；否则，返回 false 。
 *
 * @author wuzheng.yk
 * @date 2024/2/23
 */
public class LC55jump_game {

    public boolean canJump(int[] nums) {
        int step = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (step < i) {
                return false;
            }
            step = Math.max(step, nums[i] + i);
        }
        return true;
    }
}
