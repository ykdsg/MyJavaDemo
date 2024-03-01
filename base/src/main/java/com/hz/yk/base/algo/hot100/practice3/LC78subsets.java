package com.hz.yk.base.algo.hot100.practice3;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.cn/problems/subsets/description/
 * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 *
 * @author wuzheng.yk
 * @date 2024/3/1
 */
public class LC78subsets {

    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        boolean[] used = new boolean[nums.length];
        dfs(nums, new ArrayList<>(), used, 0);
        return result;
    }

    private void dfs(int[] nums, ArrayList<Integer> path, boolean[] used, int start) {
        result.add(new ArrayList<>(path));
        for (int i = start; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            path.add(nums[i]);
            used[i] = true;
            dfs(nums, path, used, i + 1);
            used[i] = false;
            path.remove(path.size() - 1);

        }
    }

    public static void main(String[] args) {
        LC78subsets test = new LC78subsets();
        int[] nums = { 1, 2, 3 };
        final List<List<Integer>> subsets = test.subsets(nums);
        System.out.println(subsets);
    }

}
