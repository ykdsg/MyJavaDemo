package com.hz.yk.base.algo.camp.ch13_trie;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 *
 * @author wuzheng.yk
 * @date 2023/10/18
 */
public class LC200number_of_islands {

    //这里只要判断跟之前遍历过的岛屿是否相同，所以只要判断左边和上边
    final static int[][] dirs = { { -1, 0 }, { 0, -1 } };
    int row;
    int col;

    public int numIslands(char[][] grid) {
        //行数
        row = grid.length;
        //列数
        col = grid[0].length;
        UnionFindArr uf = new UnionFindArr(row * col);
        //因为每个0 算作一个单独的岛屿，所以最后计算的时候要减去
        int zeroCount = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == '1') {
                    checkAndMerge(grid, i, j, uf);
                } else {
                    zeroCount++;
                }
            }
        }

        return uf.getCount() - zeroCount;
    }

    private void checkAndMerge(char[][] grid, int x, int y, UnionFindArr uf) {
        for (int[] dir : dirs) {
            int nx = x + dir[0];
            int ny = y + dir[1];
            if (nx < 0 || nx >= row || ny < 0 || ny >= col || grid[nx][ny] != '1') {
                continue;
            }
            uf.merge(getIndex(x, y), getIndex(nx, ny));
        }

    }

    private int getIndex(int x, int y) {
        return x * col + y;
    }

    @Test
    public void test() {
        LC200number_of_islands test = new LC200number_of_islands();
        char[][] grid = { { '1', '1', '1', '1', '0' }, { '1', '1', '0', '1', '0' }, { '1', '1', '0', '0', '0' },
                          { '0', '0', '0', '0', '0' } };
        final int result = test.numIslands(grid);
        Assertions.assertEquals(1, result);
    }
}
