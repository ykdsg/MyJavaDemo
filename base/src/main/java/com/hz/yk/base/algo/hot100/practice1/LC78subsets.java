package com.hz.yk.base.algo.hot100.practice1;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.cn/problems/subsets/description/
 * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 *
 * @author wuzheng.yk
 * @date 2024/3/6
 */
public class LC78subsets {

    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        dfs(nums, new ArrayList<>(), 0);

        return result;
    }

    private void dfs(int[] nums, ArrayList<Integer> path, int begin) {
        result.add(new ArrayList<>(path));
        for (int i = begin; i < nums.length; i++) {
            path.add(nums[i]);
            //因为不走回头路，所以这里不能用begin+1 ，而是用i+1
            dfs(nums, path, i + 1);
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        LC78subsets test = new LC78subsets();
        int[] nums = { 1, 2, 3 };
        System.out.println(test.subsets(nums));
    }
}
