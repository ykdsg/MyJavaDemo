package com.hz.yk.base.algo.hot100.practice4;

/**
 * https://leetcode.cn/problems/median-of-two-sorted-arrays/description/
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
 *
 * @author wuzheng.yk
 * @date 2024/1/31
 */
public class LC004median_of_two {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        final int len = nums1.length + nums2.length;
        int k = len / 2;
        if (len % 2 == 1) {
            return getKthElement(nums1, nums2, 0, 0, k + 1);
        } else {
            return (getKthElement(nums1, nums2, 0, 0, k) + getKthElement(nums1, nums2, 0, 0, k + 1)) / 2.0;
        }
    }

    /**
     * 找出第k个元素
     *
     * @param nums1
     * @param nums2
     * @param index1 num1起始下标
     * @param index2 num2起始下标
     * @param k
     * @return
     */
    private int getKthElement(int[] nums1, int[] nums2, int index1, int index2, int k) {
        //递归返回情况
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
            //因为newIndex1 本身也舍弃了，所以 k要减去newIndex1 - index1 + 1
            return getKthElement(nums1, nums2, newIndex1 + 1, index2, k - (newIndex1 - index1 + 1));
        } else {
            return getKthElement(nums1, nums2, index1, newIndex2 + 1, k - (newIndex2 - index2 + 1));
        }

    }

    public static void main(String[] args) {
        LC004median_of_two test = new LC004median_of_two();
        final double result = test.findMedianSortedArrays(new int[]{ 1, 3 }, new int[]{ 2, 7 });
        System.out.println(result);
    }
}
