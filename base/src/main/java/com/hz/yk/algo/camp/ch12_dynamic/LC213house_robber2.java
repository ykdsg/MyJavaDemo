package com.hz.yk.algo.camp.ch12_dynamic;

import java.util.Arrays;

/**
 * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
 * 
 * https://leetcode.cn/problems/house-robber-ii/description/
 * @author wuzheng.yk
 * @date 2023/7/29
 */
public class LC213house_robber2 {
    //跟 198的区别是多了一个环状的限制
    //可以拆解成2个子问题，
    //1.在不偷第一个房子的情况下nums[1:]，最大金额是p1
    //2.在不偷最后一个房子的情况下nums[0:n-1]，最大金额是p2
    //最后的金额：max(p1,p2)
    public int rob(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int p1 = singleRob(Arrays.copyOfRange(nums, 1, nums.length));
        int p2 = singleRob(Arrays.copyOfRange(nums, 0, nums.length - 1));
        return Math.max(p1, p2);
    }

    // 单纯计算单排的情况
    public int singleRob(int[] nums) {
        int pre = 0;
        int cur = 0;
        for (int num : nums) {
            int tmp = cur;
            cur = Math.max(cur, pre + num);
            pre = tmp;
        }
        return cur;
    }
}
