package com.hz.yk.algo.camp.ch11_binary;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 给你一个满足下述两条属性的 m x n 整数矩阵：
 *
 * 每行中的整数从左到右按非递减顺序排列。
 * 每行的第一个整数大于前一行的最后一个整数。
 * 给你一个整数 target ，如果 target 在矩阵中，返回 true ；否则，返回 false 。
 * @author wuzheng.yk
 * @date 2023/8/4
 */
public class LC74search_a_2d_matrix {
    
    //先通过一个2分查到行，再通过一个2分在行中找到值
    public boolean searchMatrix(int[][] matrix, int target) {
        int rowLeft = 0, rowRight = matrix.length - 1;
        int columnSize = matrix[0].length;
        int row = -1;
        while (rowLeft <= rowRight) {
            int mid = rowLeft + (rowRight - rowLeft) / 2;
            if(matrix[mid][0] == target) {
                return true;
            } else if (matrix[mid][0] > target) {
            //    说明在上半边
                rowRight = mid - 1;
            }else {
                //说明在下半边或者当前行
                if (matrix[mid][columnSize - 1] >= target) {
                    //在当前行的情况
                    row = mid;
                    break;
                }else {
                    rowLeft = mid + 1;
                }
            }
        }
        //如果没办法定位到行，那就找不到
        if (row == -1) {
            return false;
        }
        return searchInRow(matrix[row], target);
        
    }

    /**
     * 行内2分查找
     * @param nums
     * @param target
     * @return
     */
    private boolean searchInRow(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return true;
            } else if (nums[mid] < target) {
                left = mid + 1;
            }else {
                right = mid - 1;
            }
        }
        return false;
    }

    //实际上2维有序矩阵依次首尾相连，也是有序的。因此可以当做一维数组来做
    //只是在实际取值的时候需要对mid 进行处理。
    //整体思路巧妙，代码能减少一倍以上
    public boolean searchMatrix2(int[][] matrix, int target) {
        int column = matrix[0].length;
        int left = 0, right = matrix.length * column- 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            final int midValue = matrix[mid / column][mid % column];
            if (midValue == target) {
                return true;
            } else if (midValue < target) {
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }
        return false;
    }

    @Test
    public void test(){
        LC74search_a_2d_matrix test = new LC74search_a_2d_matrix();
        int[][] nums = new int[3][4];
        nums[0] = new int[]{ 1, 3, 5, 7 };
        nums[1] = new int[]{ 10, 11, 16, 20 };
        nums[2] = new int[]{ 23, 30, 34, 60 };

        final boolean isTrue = test.searchMatrix2(nums, 3);
        assertTrue(isTrue);

        final boolean isFalse = test.searchMatrix2(nums, 4);
        assertFalse(isFalse);
    }
}
