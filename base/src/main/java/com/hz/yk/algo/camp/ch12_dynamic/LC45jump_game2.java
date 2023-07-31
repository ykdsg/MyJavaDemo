package com.hz.yk.algo.camp.ch12_dynamic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 给定一个长度为 n 的 0 索引整数数组 nums。初始位置为 nums[0]。
 * 每个元素 nums[i] 表示从索引 i 向前跳转的最大长度。换句话说，如果你在 nums[i] 处，你可以跳转到任意 nums[i + j] 处:
 * 返回到达 nums[n - 1] 的最小跳跃次数。生成的测试用例可以到达 nums[n - 1]。
 * https://leetcode.cn/problems/jump-game-ii/
 * @author wuzheng.yk
 * @date 2023/7/31
 */
public class LC45jump_game2 {


    //动态规划的解法
    //递推方程：f[i] = min(f[j]...) +1
    //f[j] 表示所有符合 f[j]+nums[j]>=i 的数据 
    public int jump(int[] nums) {
        final int n = nums.length;
        int[] dp = new int[n];
        dp[0] = 0;
        for (int i = 1; i < n; i++) {
            int min = n;
            for (int j = 0; j < i; j++) {
                //说明j 这块石头跳不到i 那里
                if (nums[j] + j < i) {
                    continue;
                }
                min = Math.min(min, dp[j]);
            }
            dp[i] = min + 1;
        }

        return dp[n - 1];
    }

    //使用贪心算法，贪心算法的效率高很多
    public int jump2(int[] nums) {
        //start 表示可选择的起跳范围开始的格子
        int start=0, steps = 0;
        // end 表示可选择的起跳范围结束的格子（不包含）
        int end = 1;
        while (end < nums.length) {
            //这一轮跳跃中最大能抵达的坐标
            int maxPos = 0;
            for (int i = start; i < end; i++) {
                maxPos = Math.max(maxPos, i + nums[i]);
            }
            start = end;
            end = maxPos + 1;
            steps++;
        }
        return steps;
    } 

    @Test
    public  void test() {
        LC45jump_game2 test = new LC45jump_game2();
        int[] case2 = new int[]{ 2, 3, 1, 1, 4 };
        assertEquals(2,test.jump(case2));

        case2 = new int[]{ 2, 3, 0, 1, 4 };
        assertEquals(2,test.jump(case2));
                
        
    }
}
