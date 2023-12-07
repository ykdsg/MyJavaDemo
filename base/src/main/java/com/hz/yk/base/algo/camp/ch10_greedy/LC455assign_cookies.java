package com.hz.yk.base.algo.camp.ch10_greedy;

import java.util.Arrays;

/**
 * 假设你是一位很棒的家长，想要给你的孩子们一些小饼干。但是，每个孩子最多只能给一块饼干。
 * 对每个孩子 i，都有一个胃口值 g[i]，这是能让孩子们满足胃口的饼干的最小尺寸；并且每块饼干 j，都有一个尺寸 s[j] 。如果 s[j] >= g[i]，我们可以将这个饼干 j 分配给孩子 i ，这个孩子会得到满足。你的目标是尽可能满足越多数量的孩子，并输出这个最大数值。
 * https://leetcode.cn/problems/assign-cookies/description/
 *
 * @author wuzheng.yk
 * @date 2023/8/8
 */
public class LC455assign_cookies {

    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int count = 0;

        for (int i = 0, j = 0; i < g.length && j < s.length; i++, j++) {

            //如果饼干不能满足小朋友，寻找下一块
            while (j < s.length && g[i] > s[j]) {
                j++;
            }
            //说明存在满足小朋友的饼干
            if (j < s.length) {
                count++;
            }

        }

        return count;

    }

    //更简洁的写法，从胃口大的开始，这样饼干的下标就不用每次移动
    public int findContentChildren2(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int count = 0;
        //饼干下标
        int index = s.length - 1;
        for (int i = g.length - 1; i >= 0; i--) {
            //如果饼干能满足胃口，注意index需要保证不越界
            if (index >= 0 && s[index] >= g[i]) {
                index--;
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        LC455assign_cookies test = new LC455assign_cookies();
        final int result = test.findContentChildren2(new int[]{ 1, 3, 2 }, new int[]{ 1, 1 });
        System.out.println(result);
    }
}
