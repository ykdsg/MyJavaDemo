package com.hz.yk.base.algo.hot100.practice1;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.cn/problems/combination-sum/description/
 * 给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
 * candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
 *
 * @author wuzheng.yk
 * @date 2024/3/5
 */
public class LC39combination_sum {

    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (candidates.length == 0) {
            return result;
        }
        dfs(candidates, new ArrayList<>(), 0, target);
        return result;
    }

    /**
     * 注意这里需要一个start 标识，用来避免走回头路引起的重复数据
     *
     * @param candidates
     * @param path
     * @param start
     * @param target
     */
    private void dfs(int[] candidates, ArrayList<Integer> path, int start, int target) {
        if (target == 0) {
            result.add(new ArrayList<>(path));
            return;
        }
        if (target < 0) {
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            path.add(candidates[i]);
            dfs(candidates, path, i, target - candidates[i]);
            path.remove(path.size() - 1);
        }

    }

    public static void main(String[] args) {
        LC39combination_sum test = new LC39combination_sum();
        int[] nums = { 2, 3, 6, 7 };
        final List<List<Integer>> result = test.combinationSum(nums, 7);
        System.out.println(result);
    }

}
