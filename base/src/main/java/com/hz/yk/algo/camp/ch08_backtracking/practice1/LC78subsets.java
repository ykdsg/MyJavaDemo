package com.hz.yk.algo.camp.ch08_backtracking.practice1;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 * 
 * https://leetcode.cn/problems/subsets/
 * @author wuzheng.yk
 * @date 2023/8/9
 */
public class LC78subsets {

    List<List<Integer>> result = new ArrayList<>();
    public List<List<Integer>> subsets(int[] nums) {
        dfs(0, nums, new ArrayList<>());
        return result;
    }

    void dfs(int layer, int[] nums, List<Integer> path) {
        if (layer >= nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }
        //case1：当前字符不加入
        dfs(layer + 1, nums, path);
        //case2:当前字符加入
        final ArrayList<Integer> nPath = new ArrayList<>(path);
        nPath.add(nums[layer]);
        dfs(layer + 1, nums, nPath);
    }

    public static void main(String[] args) {
        LC78subsets test = new LC78subsets();
        final List<List<Integer>> result = test.subsets(new int[]{ 1, 2, 3 });
        System.out.println(result);
    }
}
