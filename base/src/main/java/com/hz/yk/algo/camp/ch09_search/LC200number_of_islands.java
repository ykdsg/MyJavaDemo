package com.hz.yk.algo.camp.ch09_search;

/**
 * @author wuzheng.yk
 * @date 2023/7/27
 */
public class LC200number_of_islands {

    public int numIslands(char[][] grid) {
        int result = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                //如果是水直接跳过
                if (grid[i][j] == '0') {
                    continue;
                }
                //说明遇到陆地
                result++;
                //通过递归将连在一起的陆地沉没掉
                dfs(i, j, grid);
            }
        }

        return result;
    }

    /**
     * 沉水算法，将相邻的区块沉没，这样剩下的就是跟当前区块不相连的板块
     * @param x
     * @param y
     * @param grid
     */
    private void dfs(int x, int y, char[][] grid) {
        if (x < 0 || y < 0 || x >= grid.length || y >= grid[x].length || grid[x][y] == '0') {
            return;
        }
        grid[x][y] = '0';
        int[] xbuf = new int[]{ 1, -1, 0, 0 };
        int[] ybuf = new int[]{ 0, 0, 1, -1 };
        //下探到临近区块
        for (int i = 0; i < 4; i++) {
            dfs(x + xbuf[i], y + ybuf[i], grid);
        }
    }

    public static void main(String[] args) {
        char[][] grid = new char[][]{ { '1', '1', '0', '0', '0' }, { '1', '1', '0', '0', '0' },
                                      { '0', '0', '1', '0', '0' }, { '0', '0', '0', '1', '1' } };

        LC200number_of_islands test = new LC200number_of_islands();
        final int result = test.numIslands(grid);
        System.out.println(result);
    }
}
