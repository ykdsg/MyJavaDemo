package com.hz.yk.base.algo.hot100;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.cn/problems/combination-sum/description/
 * 给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
 * candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
 *
 * @author wuzheng.yk
 * @date 2024/2/4
 */
public class LC39combination_sum {

    List<List<Integer>> result = new ArrayList<>();

    /**
     * 难点是怎么去除重复的组合
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (candidates.length == 0) {
            return result;
        }
        dsf(new ArrayList<Integer>(), 0, candidates, target);
        return result;

    }

    /**
     * 这里通过搜索起点的控制，保证了没有重复的组合
     *
     * @param path
     * @param begin
     * @param candidates
     * @param target
     */
    private void dsf(List<Integer> path, int begin, int[] candidates, int target) {
        if (target == 0) {
            result.add(new ArrayList<>(path));
            return;
        }
        if (target < 0) {
            return;
        }
        for (int i = begin; i < candidates.length; i++) {
            path.add(candidates[i]);
            // 因为允许重复使用同一个元素，所以这里下一轮的起点还是i
            dsf(path, i, candidates, target - candidates[i]);
            // 注意这里要清理掉当前层的影响
            path.remove(path.size() - 1);
        }
    }
}
