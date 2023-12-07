package com.hz.yk.base.algo.camp.ch12_dynamic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 判断你是否能够到达最后一个下标。
 * https://leetcode.cn/problems/jump-game/
 *
 * @author wuzheng.yk
 * @date 2023/7/30
 */
public class LC55jump_game {

    //可以想象成河里面的一块块石头，如果每个位置都有石头，肯定可以过去
    //如果有些地方没有石头nums[i]==0，那就要看之前积累的势能是否足够过去
    //势能的计算（递推方程）dp[i]=max[dp[0]..dp[i-1]]-1
    // 因为当前这格消耗掉一个势能所以-1
    public boolean canJump(int[] nums) {
        final int n = nums.length;
        int dp = 0;
        for (int i = 1; i < n; i++) {
            dp = Math.max(dp, nums[i - 1]) - 1;
            if (dp < 0) { //说明没有足够的势能可以跳到当前的石头
                return false;
            }
        }
        //说明有足够势能过来
        return true;
    }

    @Test
    public void test() {
        LC55jump_game test = new LC55jump_game();
        int[] caseTrue = new int[]{ 2, 3, 1, 1, 4 };
        int[] caseFalse = new int[]{ 3, 2, 1, 0, 4 };
        assertTrue(test.canJump(caseTrue));
        assertFalse(test.canJump(caseFalse));
    }
}
