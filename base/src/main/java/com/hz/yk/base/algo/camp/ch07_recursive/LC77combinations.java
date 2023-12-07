package com.hz.yk.base.algo.camp.ch07_recursive;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
 * @author wuzheng.yk
 * @date 2023/7/25
 */
public class LC77combinations {

    List<List<Integer>> resultList = new ArrayList<>();

    public List<List<Integer>> combine2(int n, int k) {
        if (k <= 0 || n < k) {
            return resultList;
        }
        create2(new ArrayList<>(), 1, n, k);
        return resultList;
    }

    void create2(List<Integer> result, int begin, int n, int k) {
        if (result.size() == k) {
            resultList.add(result);
            return;
        }
        for (int i = begin; i <= n; i++) {
            List<Integer> newResult = new ArrayList<>(result);
            //当前组合增加一个数字
            newResult.add(i);
            // 带着当前层数据下探到下一层
            create2(newResult, i+1, n, k);
        }
    }

    public List<List<Integer>> combine(int n, int k) {
        List<Integer> aryList = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            aryList.add(i);
        }
        create(new ArrayList<>(), aryList, k);
        return resultList;
    }

    /**
     * 使用递归的方式，只要考虑有k个格子，当前层可以怎么放，放好后进入下一层相应的资源是什么，同时注意不要污染当前层的数据
     *
     * @param result
     * @param ary
     * @param k
     */
    void create(List<Integer> result, List<Integer> ary, int k) {
        if (result.size() == k) {
            resultList.add(result);
            return;
        }
        for (int i = 0; i < ary.size(); i++) {
            //这里需要新创建一个newResult，否则会污染原result 在当前层的继续使用
            List<Integer> newResult = new ArrayList<>(result);
            newResult.add(ary.get(i));
            // 因为前面的元素已经被用过，所以这里直接subList 进行剪枝，避免了最后结果里面存在重复值
            List<Integer> newAry = ary.subList(i + 1, ary.size());
            create(newResult, newAry, k);
        }
    }

    public static void main(String[] args) {
        LC77combinations test = new LC77combinations();
        //final List<List<Integer>> combine = test.combine(4, 2);
        //System.out.println(combine);

        List<List<Integer>> combine = test.combine2(4, 2);
        System.out.println(combine);
    }
}
