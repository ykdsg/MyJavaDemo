package com.hz.yk.base.algo.hot100.practice3;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.cn/problems/subsets/description/
 * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 *
 * @author wuzheng.yk
 * @date 2024/4/21
 */
public class LC78subsets {

    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        dfs(nums, new ArrayList<Integer>(), 0);
        return result;
    }

    private void dfs(int[] nums, List<Integer> path, int level) {
        result.add(new ArrayList<>(path));
        for (int i = level; i < nums.length; i++) {
            path.add(nums[i]);
            dfs(nums, path, i + 1);
            path.remove(path.size() - 1);
        }
    }

}
