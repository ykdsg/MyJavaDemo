package com.hz.yk.base.algo.hot100.practice9;

/**
 * https://leetcode.cn/problems/median-of-two-sorted-arrays/description/
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数
 *
 * @author wuzheng.yk
 * @date 2024/2/25
 */
public class LC004median_of_two {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        final int len = nums1.length + nums2.length;
        int k = len / 2 + 1;
        if (len % 2 == 1) {
            return getKth(nums1, nums2, 0, 0, k);
        } else {
            return (getKth(nums1, nums2, 0, 0, k) + getKth(nums1, nums2, 0, 0, k - 1)) / 2.0;
        }
    }

    private int getKth(int[] nums1, int[] nums2, int start1, int start2, int k) {
        if (start1 >= nums1.length) {
            return nums2[start2 + k - 1];
        }
        if (start2 >= nums2.length) {
            return nums1[start1 + k - 1];
        }
        if (k == 1) {
            return Math.min(nums1[start1], nums2[start2]);
        }
        int half = k / 2;
        int newIndex1 = Math.min(nums1.length, start1 + half) - 1;
        int newIndex2 = Math.min(nums2.length, start2 + half) - 1;
        if (nums1[newIndex1] <= nums2[newIndex2]) {
            return getKth(nums1, nums2, newIndex1 + 1, start2, k - (newIndex1 - start1 + 1));
        } else {
            return getKth(nums1, nums2, start1, newIndex2 + 1, k - (newIndex2 - start2 + 1));
        }
    }

}
