package com.hz.yk.base.algo.hot100.practice1;

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

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (candidates.length == 0) {
            return result;
        }
        dsf(new ArrayList<Integer>(), 0, candidates, target);
        return result;
    }

    private void dsf(List<Integer> path, int begin, int[] candidates, int target) {
        if (target == 0) {
            //这里需要重新new，否则后续递归会对当前数据产生影响
            result.add(new ArrayList<>(path));
            return;
        }
        //进行剪枝
        if (target < 0) {
            return;
        }
        for (int i = begin; i < candidates.length; i++) {
            path.add(candidates[i]);
            dsf(path, i, candidates, target - candidates[i]);
            path.remove(path.size() - 1);
        }
    }
}
