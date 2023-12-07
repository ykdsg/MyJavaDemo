package com.hz.yk.base.algo.camp.ch12_dynamic;

/**
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 *
 * @author wuzheng.yk
 * @date 2023/7/27
 */
public class LC70climbing_stairs {

    //之前用了递归+cache，这里使用动态规划
    public int climbStairs(int n) {
        if (n <= 2) {
            return n;
        }
        //这里为了方便表示1级台阶对应的就是dp[1]，因此多1位长度
        int[] dp = new int[n + 1];
        //初始化状态表
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            //这个就是爬楼梯的动态方程，相当于当前这级台阶要么是跨1步上来的或者跨2步上来的
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    /**
     * 爬楼梯增强版：一次可以爬1、2、3个台阶，但是要求相邻两步步伐不能相同
     *
     * 需要用到动态规划，难点是怎么构造状态表和动态方程
     * @param n
     * @return
     */
    public int climbStairsEnhance(int n) {
        if (n <= 2) {
            return 1;
        } else if (n == 3) {
            return 3;
        }
        //需要一个2维矩阵来表示状态表，横轴表示台阶数，纵轴表示最后一步是跨几个台阶上来的
        //比如dp[3][1] 表示3级台阶，最后一步是跨1步上来的走法有几种，dp[3][2] 表示3级台阶最后一步跨2步上来的走法数量。
        int[][] dp = new int[n + 1][4];
        dp[1][1] = 1;
        //2 级台阶走1步上来不符合相邻步伐不能相同的规则，所以只能跨2步走上来
        dp[2][2] = 1;
        // 3级台阶的情况
        dp[3][1] = 1;
        dp[3][2] = 1;
        dp[3][3] = 1;

        for (int i = 4; i <= n; i++) {
            //状态方程，i级台阶最后跨1步的情况，是i-1级 最后跨2步+3步的情况，因为不能算上跨1步的数量
            dp[i][1] = dp[i - 1][2] + dp[i - 1][3];
            //下面几步也是类似道理
            dp[i][2] = dp[i - 1][1] + dp[i - 1][3];
            dp[i][3] = dp[i - 1][1] + dp[i - 1][2];
        }
        return dp[n][1]+dp[n][2]+dp[n][3];
    }

        public static void main (String[]args){
            LC70climbing_stairs test = new LC70climbing_stairs();
            final int result = test.climbStairs(3);
            System.out.println(result);

            final int num = test.climbStairsEnhance(4);
            System.out.println("enhance:"+num);
        }
    }
