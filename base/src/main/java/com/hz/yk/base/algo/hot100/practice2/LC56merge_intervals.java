package com.hz.yk.base.algo.hot100.practice2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.cn/problems/merge-intervals/description/
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
 *
 * @author wuzheng.yk
 * @date 2024/2/18
 */
public class LC56merge_intervals {

    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        List<int[]> result = new ArrayList<>();
        for (int[] interval : intervals) {
            //如果结果集为空或者数组的starti 于结果集最新数字的endi ，说明不在同一个区间
            if (result.isEmpty() || interval[0] > result.get(result.size() - 1)[1]) {
                result.add(interval);
            } else {
                //存在合并的情况
                final int[] ints = result.get(result.size() - 1);
                ints[1] = Math.max(ints[1], interval[1]);
            }
        }
        return result.toArray(new int[0][]);
    }
}
