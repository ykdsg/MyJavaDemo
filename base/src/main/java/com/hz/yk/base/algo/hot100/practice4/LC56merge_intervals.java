package com.hz.yk.base.algo.hot100.practice4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.cn/problems/merge-intervals/description/
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
 *
 * @author wuzheng.yk
 * @date 2024/2/28
 */
public class LC56merge_intervals {

    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        List<int[]> result = new ArrayList<>();
        for (int[] interval : intervals) {
            //如果是第一个或者超过结果集最上面的下标
            if (result.isEmpty() || interval[0] > result.get(result.size() - 1)[1]) {
                result.add(interval);
            } else { //需要合并的情况
                final int[] ints = result.get(result.size() - 1);
                // 注意这里不能直接是interval[1] ，而是取max
                ints[1] = Math.max(ints[1], interval[1]);
            }
        }
        return result.toArray(new int[0][]);
    }

}
