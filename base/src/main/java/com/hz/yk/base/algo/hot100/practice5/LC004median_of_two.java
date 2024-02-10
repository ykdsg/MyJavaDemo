package com.hz.yk.base.algo.hot100.practice5;

/**
 * https://leetcode.cn/problems/median-of-two-sorted-arrays/description/
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
 *
 * @author wuzheng.yk
 * @date 2024/2/8
 */
public class LC004median_of_two {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len = nums1.length + nums2.length;
        int mid = len / 2;
        if (len % 2 == 1) {
            return getKthElement(nums1, nums2, 0, 0, mid);
        } else {
            return (getKthElement(nums1, nums2, 0, 0, mid - 1) + getKthElement(nums1, nums2, 0, 0, mid)) / 2.0;
        }
    }

    /**
     * 查找第k个数
     *
     * @param nums1
     * @param nums2
     * @param index1 num1起始下标
     * @param index2 num2起始下标
     * @param k
     * @return
     */
    private int getKthElement(int[] nums1, int[] nums2, int index1, int index2, int k) {
        if (index1 >= nums1.length) {
            return nums2[index2 + k - 1];
        }
        if (index2 >= nums2.length) {
            return nums1[index1 + k - 1];
        }
        if (k == 1) {
            return Math.min(nums1[index1], nums2[index2]);
        }
        int mid = k / 2;
        int newIndex1 = Math.min(index1 + mid, nums1.length) - 1;
        int newIndex2 = Math.min(index2 + mid, nums2.length) - 1;
        if (nums1[newIndex1] <= nums2[newIndex2]) {
            return getKthElement(nums1, nums2, newIndex1 + 1, newIndex2, k - (newIndex1 - index1) - 1);
        } else {
            return getKthElement(nums1, nums2, newIndex1, newIndex2 + 1, k - (newIndex2 - index2) - 1);
        }

    }

}
