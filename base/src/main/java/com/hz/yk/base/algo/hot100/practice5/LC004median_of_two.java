package com.hz.yk.base.algo.hot100.practice5;

/**
 * https://leetcode.cn/problems/median-of-two-sorted-arrays/description/
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
 *
 * @author wuzheng.yk
 * @date 2024/2/16
 */
public class LC004median_of_two {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        final int len = nums1.length + nums2.length;
        int k = len / 2 + 1;
        //奇数个的情况
        if (len % 2 == 1) {
            return getKth(nums1, nums2, k, 0, 0);
        } else {
            return (getKth(nums1, nums2, k, 0, 0) + getKth(nums1, nums2, k - 1, 0, 0)) / 2.0;
        }
    }

    /**
     * 找到第k个数字
     *
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */
    private int getKth(int[] nums1, int[] nums2, int k, int index1, int index2) {
        if (index1 >= nums1.length) {
            return nums2[index2 + k - 1];
        }
        if (index2 >= nums2.length) {
            return nums1[index1 + k - 1];
        }
        if (k == 1) {
            return Math.min(nums1[index1], nums2[index2]);
        }
        int half = k / 2;
        int newIndex1 = Math.min(index1 + half, nums1.length) - 1;
        int newIndex2 = Math.min(index2 + half, nums2.length) - 1;
        if (nums1[newIndex1] <= nums2[newIndex2]) {
            return getKth(nums1, nums2, k - (newIndex1 - index1 + 1), newIndex1 + 1, index2);
        } else {
            return getKth(nums1, nums2, k - (newIndex2 - index2 + 1), index1, newIndex2 + 1);
        }
    }
}
