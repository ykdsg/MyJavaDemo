package com.hz.yk.base.algo.camp.ch13_trie;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 给你一个 n x n 的矩阵 isConnected ，其中 isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连，而 isConnected[i][j] = 0 表示二者不直接相连。
 * 返回矩阵中 省份 的数量。
 *
 * @author wuzheng.yk
 * @date 2023/10/17
 */
public class LC547number_of_provinces {

    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        UnionFind uf = new UnionFind();
        for (int i = 0; i < isConnected.length; i++) {
            //相当于增加每个城市
            uf.add(i);
            for (int j = 0; j < i; j++) {
                //如果有直接相连的城市，可以合并集合
                if (isConnected[i][j] == 1) {
                    uf.merge(i, j);
                }
            }
        }
        return uf.getCount();
    }

    @Test
    public void test() {
        LC547number_of_provinces test = new LC547number_of_provinces();
        int[][] isConnected = { { 1, 1, 0 }, { 1, 1, 0 }, { 0, 0, 1 } };
        final int result = test.findCircleNum(isConnected);
        Assertions.assertEquals(2, result);

    }
}

