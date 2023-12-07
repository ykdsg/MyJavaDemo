package com.hz.yk.base.algo.camp.ch12_dynamic;

/**
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 * https://leetcode.cn/problems/house-robber/
 *
 * @author wuzheng.yk
 * @date 2023/7/28
 */
public class LC198house_robber {

    //递推方程：f[i][0]=max(f[i-1][0] ,f[i-1][1]),f[i][1]=f[i-1][0]+nums[i]
    //f[i][0] 表示i这个房子偷，f[i][1] 表示这个房子不偷
    //状态方程确实容易采坑，之前就没想到f[i][0] 应该使用max，任务就是f[i-1][1]
    public int rob(int[] nums) {
        int[][] dp = new int[nums.length][2];
        dp[0][0] = 0;
        dp[0][1] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);
            dp[i][1] = dp[i - 1][0] + nums[i];
        }
        return Math.max(dp[nums.length - 1][0], dp[nums.length - 1][1]);
    }

    //这里的状态方程调整了下
    //f[i] 表示 到i这个房子为止偷来的最大值
    //f[i] = max(f[i-1],f[i-2]+nums[i])，就是不管i-1 间偷还是不偷只要保证f[i-1]是那几间里的最大值就行了
    // 分析一下就可以知道：如果i-1 偷，那么f[i-1]>f[i-2]，如果i-1 不偷，那么f[i-1]==f[i-2]
    public int rob2(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        final int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[n-1];
    }


    // 在上面的基础上简化内存使用，这个就非常简洁了
    public int rob3(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int pre = 0;
        int cur = nums[0];
        for (int i = 2; i <= nums.length; i++) {
            int tmp = cur;
            cur = Math.max(cur, pre + nums[i - 1]);
            pre = tmp;
        }
        return cur;
    }

    /**
     * 在上一步的基础上就更简洁了，而且兼容性更好，不需要再提前判断了
     * @param nums
     * @return
     */
    public int rob4(int[] nums) {
        int pre = 0;
        int cur = 0;
        for (int num : nums) {
            int tmp = cur;
            cur = Math.max(cur, pre + num);
            pre = tmp;
        }
        return cur;
    }

    public static void main(String[] args) {
        LC198house_robber test = new LC198house_robber();
        final int result = test.rob4(new int[]{ 2, 1, 1, 2 });
        System.out.println(result);

    }
}
