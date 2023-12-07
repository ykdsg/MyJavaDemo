package com.hz.yk.base.algo.camp.ch10_greedy;

import java.util.HashSet;
import java.util.Set;

/**
 * 机器人在一个无限大小的 XY 网格平面上行走，从点 (0, 0) 处开始出发，面向北方。该机器人可以接收以下三种类型的命令
 * 返回机器人距离原点的 最大欧式距离 的 平方
 * 1 <= commands.length <= 104
 * commands[i] 的值可以取 -2、-1 或者是范围 [1, 9] 内的一个整数。
 * https://leetcode.cn/problems/walking-robot-simulation/description/
 * @author wuzheng.yk
 * @date 2023/8/9
 */
public class LC874walking_robot_simulation {
    //使用哈希表缓存障碍物
    public int robotSim(int[] commands, int[][] obstacles) {
        // 表示朝向北、东、南、西 方向行走一步的坐标变化
        int[] direX = new int[]{ 0, 1, 0, -1 };
        int[] direY = new int[]{ 1, 0, -1, 0 };

        Set<Integer> obstCache = new HashSet<>();
        final double uplimit = Math.pow(10, 5);
        final double lowlimit = Math.pow(-10, 5);

        for (int[] obstacle : obstacles) {
            //根据上面command 的范围，就算全朝一个方向走，也不可能走出相应的范围大小，所以障碍物在这个范围外不用考虑
            if (obstacle[0] > lowlimit && obstacle[0] < uplimit && obstacle[1] > lowlimit && obstacle[1]<uplimit) {
                obstCache.add((int) (uplimit * obstacle[0] + obstacle[1]));
            }
        }
        //刚开始面向北面
        int curDire = 0;
        int ans = 0;
        int curX = 0, curY = 0;
        for (int command : commands) {
            if (command == -1) {  // -1：向右转 90 度
                curDire = (curDire + 1) % 4;
            } else if (command == -2) { //-2：向左转 90 度
                curDire = (curDire + 3) % 4;
            }else {  //  1 <= x <= 9：向前移动 x 个单位长度
                for (int i = 0; i < command; i++) {
                    int nx = curX + direX[curDire];
                    int ny = curY + direY[curDire];
                    //如果是障碍那就停止等待下一步指令
                    if (obstCache.contains((int) (nx * uplimit + ny))) {
                        break;
                    }else {
                        curX = nx;
                        curY = ny;
                        ans = Math.max(ans, curX * curX + curY * curY);
                    }
                }
            }
        }
        return ans;
    }
}
