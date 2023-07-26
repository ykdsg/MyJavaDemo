package com.hz.yk.algo.camp.ch08_backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * @author wuzheng.yk
 * @date 2023/7/26
 */
public class LC51n_queens {

    List<List<Integer>> result = new ArrayList<>();
    public List<List<String>> solveNQueens(int n) {
        dsf(0, new ArrayList<Integer>(), new HashSet<>(), new HashSet<>(), new HashSet<>(), n);
        return toStrList(result, n);
    }

    void dsf(int layer, List<Integer> path, Set<Integer> pieSet, Set<Integer> naSet,Set<Integer> columnSet, int n) {
        if (layer == n) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < n; i++) {
            Integer column = i;
            Integer pie =  layer + i;
            Integer na = layer - i;
            // 列和对角线（撇和捺）有冲突，说明放不了
            if (columnSet.contains(column) || pieSet.contains(pie) || naSet.contains(na)) {
                continue;
            }
            
            // 到这里说明能够放入棋子，更新相依的列和对角线标记
            path.add(i);
            columnSet.add(column);
            pieSet.add(pie);
            naSet.add(na);
            // 下探到下一层
            dsf(layer + 1, path, pieSet, naSet, columnSet, n);
            
            //清理资源
            path.remove(path.size() - 1);
            columnSet.remove(column);
            pieSet.remove(pie);
            naSet.remove(na);
        }
    }

    private List<List<String>> toStrList(List<List<Integer>> result,int n ) {
        return result.stream().map(iList -> iList.stream().map(i -> {
            String s = "";
            for (int j = 0; j < i; j++) {
                s += ".";
            }
            s += "Q";
            for (int j = i + 1; j < n; j++) {
                s += ".";
            }
            return s;
        }).collect(Collectors.toList())).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        LC51n_queens test = new LC51n_queens();
        System.out.println(test.solveNQueens(4));
    }
}
