package com.hz.yk.algo.camp.ch12_dynamic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 一只青蛙想要过河。 假定河流被等分为若干个单元格，并且在每一个单元格内都有可能放有一块石子（也有可能没有）。 青蛙可以跳上石子，但是不可以跳入水中。
 * 给你石子的位置列表 stones（用单元格序号 升序 表示）， 请判定青蛙能否成功过河（即能否在最后一步跳至最后一块石子上）。开始时， 青蛙默认已站在第一块石子上，并可以假定它第一步只能跳跃 1 个单位（即只能从单元格 1 跳至单元格 2 ）。
 * 如果青蛙上一步跳跃了 k 个单位，那么它接下来的跳跃距离只能选择为 k - 1、k 或 k + 1 个单位。 另请注意，青蛙只能向前方（终点的方向）跳跃。
 * https://leetcode.cn/problems/frog-jump/
 * @author wuzheng.yk
 * @date 2023/7/30
 */
public class LC403frog_jump {

    //这里递推方程不太好设计，理论上最终跟2个维度有关，一个是当前所在的位置， 一个是k值
    //f[i][k] 说明在第i 块石头上，通过k 步跳过来是否可行
    //f[i][k] = f[j][k-1] || f[j][k] || f[j][k+1]
    // j=i-k ,表示在i 之前k 步的石头
    // 这里绕了几个弯：
    // 1.首先是要能想到存在k这个维度
    //2. k 还不能直接递推，要根据石头的位置，也就是j 来判断存在哪些可能的k，
    // 或者说直接递推k，再判断是否存在相应的石头也是可以的，但这样明显更费劲，需要把stones 构造成map方便进行判断
    public boolean canCross(int[] stones) {
        //因为第一步只能跳1个单位，如果这个位置没有石头，那就没得玩了
        if (stones[1] != 1) {
            return false;
        }
        final int n = stones.length;
        // 因为每次跳跃步数最多+1，所以不会超出stones.length
        boolean[][] dp = new boolean[n][n+1];
        dp[1][1] = true;
        for (int i = 2; i < n; i++) {
            for (int j = 1; j < i; j++) {
                final int k = stones[i] - stones[j];
                //如果两块石头相距太远，超过了k当时所在第j块最大能达到的限制，也肯定也过不来
                // 因此只有k 在范围内，才有进一步判断的必要
                if (k <= j+1) {
                    dp[i][k] = dp[j][k - 1] || dp[j][k] || dp[j][k + 1];
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (dp[n - 1][i]) {
                return true;
            }
        }
        return false;
    }

    @Test
    public void setTest(){
        LC403frog_jump test = new LC403frog_jump();
        int[] case_true = new int[]{ 0, 1, 3, 5, 6, 8, 12, 17 };
        assertTrue(test.canCross(case_true));

        int[] case_false = new int[]{ 0, 1, 2, 3, 4, 8, 9, 11 };
        assertFalse(test.canCross(case_false));
        
    }
}
