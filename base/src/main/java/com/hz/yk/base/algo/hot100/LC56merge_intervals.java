package com.hz.yk.base.algo.hot100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.cn/problems/merge-intervals/description/
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
 *
 * @author wuzheng.yk
 * @date 2024/2/17
 */
public class LC56merge_intervals {

    /**
     * 实际是n*2 数组
     *
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
        // 先按照区间起始位置排序
        Arrays.sort(intervals, (v1, v2) -> v1[0] - v2[0]);
        // 遍历区间
        List<int[]> result = new ArrayList<>();
        for (int[] interval : intervals) {
            // 如果结果数组是空的，或者当前区间的起始位置 > 结果数组中最后区间的终止位置，
            // 则不合并，直接将当前区间加入结果数组。
            if (result.isEmpty() || interval[0] > result.get(result.size() - 1)[1]) {
                result.add(interval);
            } else {
                final int[] ints = result.get(result.size() - 1);
                ints[1] = Math.max(ints[1], interval[1]);
            }
        }
        return result.toArray(new int[0][]);
    }
}
