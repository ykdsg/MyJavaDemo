package com.hz.yk.algo.camp.ch07_recursive;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
 * https://leetcode.cn/problems/permutations-ii/
 *
 * @author wuzheng.yk
 * @date 2023/7/25
 */
public class LC47permutations2 {

    List<List<Integer>> resultList = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        //因为涉及前后元素的对比，这里需要进行排序
        final List<Integer> numList = Arrays.stream(nums).sorted().boxed().collect(Collectors.toList());
        generate(new ArrayList<>(), numList, nums.length);
        return resultList;
    }

    void generate(List<Integer> path, List<Integer> numList, int n) {
        if (path.size() == n) {
            resultList.add(path);
            return;
        }
        for (int i = 0; i < numList.size(); i++) {
            //这里进行剪枝，因为上一个数字跟当前数字相同，说明会存在重复项，这类情况不用进行处理
            if (i > 0 && Objects.equals(numList.get(i), numList.get(i - 1))) {
                continue;
            }
            List<Integer> newPath = new ArrayList<>(path);
            newPath.add(numList.get(i));
            List<Integer> newNumList = new ArrayList<>(numList);
            newNumList.remove(i);
            generate(newPath, newNumList, n);
        }
    }

    public static void main(String[] args) {
        LC47permutations2 test = new LC47permutations2();
        final List<List<Integer>> result = test.permute(new int[]{ 1, 3, 1 });
        System.out.println(result);

    }
}
