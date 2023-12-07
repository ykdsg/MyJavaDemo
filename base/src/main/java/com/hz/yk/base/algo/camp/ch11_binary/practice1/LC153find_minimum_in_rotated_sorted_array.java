package com.hz.yk.base.algo.camp.ch11_binary.practice1;

/**
 * 已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。例如，原数组 nums = [0,1,2,4,5,6,7] 在变化后可能得到：
 * 若旋转 4 次，则可以得到 [4,5,6,7,0,1,2]
 * 若旋转 7 次，则可以得到 [0,1,2,4,5,6,7]
 * https://leetcode.cn/problems/find-minimum-in-rotated-sorted-array/
 *
 * @author wuzheng.yk
 * @date 2023/8/1
 */
public class LC153find_minimum_in_rotated_sorted_array {

    public int findMin(int[] nums) {
        int left=0,right = nums.length-1;
        while (left <= right) {
            if (left == right) {
                return nums[left];
            }
            if (right - left == 1) {
                return Math.min(nums[left], nums[right]);
            }
            //如果是有序的情况
            if (nums[left] < nums[right]) {
                return nums[left];
            }else {
                //能进入这里说明至少旋转过1次
                //可以判断最小值在无序那一边
                int mid = left + (right - left) / 2;
                // 如果左边是有序的，根据上面的判断，最小值在右边
                if (nums[left] < nums[mid]) {
                    left = mid + 1;
                }else {
                    //
                    right = mid - 1;
                }
            }

        }
        return 0;
    }


    public static void main(String[] args) {
        LC153find_minimum_in_rotated_sorted_array test = new LC153find_minimum_in_rotated_sorted_array();
        int[] int5 = new int[]{ 5, 6, 1, 2, 3, 4, 5 };
        final int min = test.findMin(int5);
        System.out.println(min);
    }
}
