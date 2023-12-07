package com.hz.yk.base.algo.camp.ch12_dynamic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author wuzheng.yk
 * @date 2023/7/28
 */
public class LC120triangle {

    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle.size() == 1) {
            return triangle.get(0).get(0);
        }
        final int size = triangle.get(triangle.size() - 1).size();
        // 这里dp 需要扩展一位，因为初始化最后一层也放在递推公式去做了，这个时候j+1 会导致数据越界
        int[] dp = new int[size+1];
        for (int i = size - 1; i >= 0; i--) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                dp[j] = Math.min(dp[j], dp[j + 1]) + triangle.get(i).get(j);
            }
        }
        return dp[0];
    }

    public static void main(String[] args) {
        List<List<Integer>> triangle = new ArrayList<>();
        triangle.add(Arrays.asList(new Integer[]{ 2 }));
        triangle.add(Arrays.asList(new Integer[]{ 3, 4 }));
        triangle.add(Arrays.asList(new Integer[]{ 6, 5, 7 }));
        triangle.add(Arrays.asList(new Integer[]{ 4, 1, 8, 3 }));

        LC120triangle test = new LC120triangle();
        final int result = test.minimumTotal(triangle);
        System.out.println(result);
    }
}
