package com.hz.yk.base.algo.hot100;

/**
 * https://leetcode.cn/problems/jump-game/description/
 * 给你一个非负整数数组 nums ，你最初位于数组的 第一个下标 。数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 判断你是否能够到达最后一个下标，如果可以，返回 true ；否则，返回 false 。
 *
 * @author wuzheng.yk
 * @date 2024/2/17
 */
public class LC55jump_game {

    /**
     * dp[i]表示在i位置能跳的最远位置
     *
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        //表示能达到的最远距离
        int k = 0;
        for (int i = 0; i < nums.length; i++) {
            if (k < i) {
                return false;
            }
            k = Math.max(k, i + nums[i]);
        }
        //能抵达最后一块石头就ok
        return true;
    }
}
