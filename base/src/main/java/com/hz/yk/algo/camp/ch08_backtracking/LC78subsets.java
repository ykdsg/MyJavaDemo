package com.hz.yk.algo.camp.ch08_backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 * 
 * https://leetcode.cn/problems/subsets/
 * @author wuzheng.yk
 * @date 2023/7/25
 */
public class LC78subsets {

    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        dsf(new ArrayList<>(), 0, nums);
        return result;
    }

    public void dsf(List<Integer> path, int layer, int[] nums) {
        if (layer >= nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }
        //当前层数据不加入的情况
        dsf(path, layer + 1, nums);
        path.add(nums[layer]);
        //当前层数据加入的情况
        dsf(path, layer + 1, nums);
        // 清理现场，也可以在穿透下一层的时候进行拷贝，这样就不会污染当前层的参数，也就不需要清理
        path.remove(path.size() - 1);
    }

    public static void main(String[] args) {
        LC78subsets test = new LC78subsets();
        final List<List<Integer>> subsets = test.subsets(new int[]{ 1, 2, 3 });
        System.out.println(subsets);
    }
}
