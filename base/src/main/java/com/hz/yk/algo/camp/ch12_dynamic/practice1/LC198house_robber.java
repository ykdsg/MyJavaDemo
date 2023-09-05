package com.hz.yk.algo.camp.ch12_dynamic.practice1;

/**
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 *  * https://leetcode.cn/problems/house-robber/
 * @author wuzheng.yk
 * @date 2023/8/30
 */
public class LC198house_robber {
    //dp[i]=Max(dp[i-1],dp[i-2]+nums[i])
    public int rob(int[] nums) {
        //因为题目保证了nums长度>=1，所以这里不做边界判断
        int[] dp = new int[nums.length ];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]); 
        for (int i = 1; i < nums.length; i++) {
            if (i == 1) {
                dp[i] = Math.max(nums[0], nums[1]);
            }else {
                dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
            } 
        }
        return dp[nums.length - 1];
    }


    public int rob2(int[] nums) {
        //通过倒过来偷，能较好解决负数下标的问题，代码更简洁
        int[] dp = new int[nums.length + 2];
        for (int i = nums.length - 1; i >= 0; i--) {
            dp[i] = Math.max(dp[i + 1], nums[i] + dp[i + 2]);
        }
        return dp[0];
    }
    public static void main(String[] args) {
        LC198house_robber test = new LC198house_robber();
        final int result = test.rob2(new int[]{ 2, 1, 1, 2 });
        System.out.println(result);

    } 
}
