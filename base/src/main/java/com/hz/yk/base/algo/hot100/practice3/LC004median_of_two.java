package com.hz.yk.base.algo.hot100.practice3;

/**
 * https://leetcode.cn/problems/median-of-two-sorted-arrays/description/
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
 *
 * @author wuzheng.yk
 * @date 2024/4/10
 */
public class LC004median_of_two {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        final int len = nums1.length + nums2.length;
        final int k = len / 2 + 1;
        if (len % 2 == 1) {
            return getKth(nums1, nums2, 0, 0, k);
        } else {
            return (getKth(nums1, nums2, 0, 0, k) + getKth(nums1, nums2, 0, 0, k - 1)) / 2.0;
        }
    }

    //获取第k个数字
    private int getKth(int[] nums1, int[] nums2, int i1, int i2, int k) {
        if (i1 == nums1.length) {
            return nums2[i2 + k - 1];
        }
        if (i2 == nums2.length) {
            return nums1[i1 + k - 1];
        }
        if (k == 1) {
            return Math.min(nums1[i1], nums2[i2]);
        }
        int mid = k / 2;
        int n1 = Math.min(nums1.length, i1 + mid) - 1;
        int n2 = Math.min(nums2.length, i2 + mid) - 1;
        if (nums1[n1] <= nums2[n2]) {
            return getKth(nums1, nums2, n1 + 1, i2, k - (n1 - i1) - 1);
        } else {
            return getKth(nums1, nums2, i1, n2 + 1, k - (n2 - i2) - 1);
        }
    }

}
