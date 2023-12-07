package com.hz.yk.base.algo.camp.ch08_backtracking.practice1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 按照国际象棋的规则，皇后可以攻击与之处在同一行或同一列或同一斜线上的棋子。
 *
 * n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 *
 * 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
 *
 * https://leetcode.cn/problems/n-queens/
 * @author wuzheng.yk
 * @date 2023/8/9
 */
public class LC51n_queens {

    List<List<Integer>> result = new ArrayList<>();

    //这里调整了下，直接输出位置即可
    public List<List<Integer>> solveNQueens(int n) {
        dfs(0,n,new HashSet<>(),new HashSet<>(),new HashSet<>(),new ArrayList<>());
        return result;
    }

    void dfs(int layer, int n, Set<Integer> columnCache, Set<Integer> pieCache, Set<Integer> naCache, List<Integer> path) {
        if (layer >= n) {
            result.add(new ArrayList<>(path));
            return;
        }
    //    当前层逻辑处理
        for (int i = 0; i < n; i++) {

            int pie = i - layer;
            int na = i + layer;
            if (columnCache.contains(i)) {
                continue;
            }
            if (pieCache.contains(pie)) {
                continue;
            }
            if (naCache.contains(na)) {
                continue;
            }
            columnCache.add(i);
            pieCache.add(pie);
            naCache.add(na);
            path.add(i);

            dfs(layer + 1, n, columnCache, pieCache, naCache, path);

            columnCache.remove(i);
            pieCache.remove(pie);
            naCache.remove(na);
            path.remove((Integer)i);
        }
    }

    public static void main(String[] args) {
        LC51n_queens test = new LC51n_queens();
        final List<List<Integer>> result = test.solveNQueens(8);
        System.out.println(result);
    }
}
