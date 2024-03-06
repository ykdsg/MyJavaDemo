package com.hz.yk.base.algo.hot100.practice1;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.cn/problems/permutations/description/
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 *
 * @author wuzheng.yk
 * @date 2024/3/5
 */
public class LC46permutations {

    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        boolean[] used = new boolean[nums.length];
        dfs(nums, new ArrayList<>(), used);
        return result;

    }

    private void dfs(int[] nums, ArrayList<Integer> path, boolean[] used) {
        if (path.size() == nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            path.add(nums[i]);
            used[i] = true;
            dfs(nums, path, used);
            used[i] = false;
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        LC46permutations test = new LC46permutations();
        final int[] nums = { 1, 2, 3 };
        final List<List<Integer>> result = test.permute(nums);
        System.out.println(result);
    }

}
