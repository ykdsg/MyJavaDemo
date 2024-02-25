package com.hz.yk.base.algo.hot100;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.cn/problems/subsets/description/
 * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 *
 * @author wuzheng.yk
 * @date 2024/2/24
 */
public class LC78subsets {

    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        dfs(nums, new ArrayList<Integer>(), 0);
        return result;
    }

    private void dfs(int[] nums, ArrayList<Integer> path, int start) {
        // 这道题比较特殊，因为他记录所有路径上的值
        // 当start 超过数组长度时自动结束递归，因此不用手写结束条件
        result.add(new ArrayList<>(path));
        for (int i = start; i < nums.length; i++) {
            path.add(nums[i]);
            dfs(nums, path, i + 1);
            path.remove(path.size() - 1);
        }
    }
}
