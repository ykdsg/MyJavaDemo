package com.hz.yk.base.algo.camp.ch07_recursive;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。
 *
 * https://leetcode.cn/problems/permutations/description/
 * @author wuzheng.yk
 * @date 2023/7/25
 */
public class LC46permutations {

    List<List<Integer>> resultList = new ArrayList<>();
    public List<List<Integer>> permute(int[] nums) {
        final List<Integer> numList = Arrays.stream(nums).boxed().collect(Collectors.toList());
        generate(new ArrayList<>(), numList, nums.length);
        return resultList;
    }

    void generate(List<Integer> path, List<Integer> numList,int n) {
        if (path.size() == n) {
            resultList.add(path);
            return;
        }
        for (int i = 0; i < numList.size(); i++) {
            List<Integer> newPath = new ArrayList<>(path);
            newPath.add(numList.get(i));
            List<Integer> newNumList = new ArrayList<>(numList);
            newNumList.remove(i);
            generate(newPath, newNumList, n);
        }
    }

    public static void main(String[] args) {
        LC46permutations test = new LC46permutations();
        final List<List<Integer>> result = test.permute(new int[]{ 1, 2, 3 });
        System.out.println(result);

    }
}
