package com.hz.yk.base.algo.hot100.practice1;

/**
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
 *
 * @author wuzheng.yk
 * @date 2024/1/20
 */
public class LC004median_of_two {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int total = nums1.length + nums2.length;
        // 奇数的情况
        if (total % 2 == 1) {
            int midIndex = total / 2;
            double median = getKthElement(nums1, nums2, midIndex + 1);
            return median;
        } else {
            int midIndex1 = total / 2 - 1, midIndex2 = total / 2;
            double median =
                    (getKthElement(nums1, nums2, midIndex1 + 1) + getKthElement(nums1, nums2, midIndex2 + 1)) / 2.0;
            return median;
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
    private int getKthElement(int[] nums1, int[] nums2, int k) {
        int length1 = nums1.length, length2 = nums2.length;
        int index1 = 0, index2 = 0;
        while (true) {
            //边界情况
            if (index1 == length1) {
                return nums2[index2 + k - 1];
            }
            if (index2 == length2) {
                return nums1[index1 + k - 1];
            }
            if (k == 1) {
                return Math.min(nums1[index1], nums2[index2]);
            }

            //    正常情况
            int half = k / 2;
            int newIndex1 = Math.min(index1 + half, length1) - 1;
            int newIndex2 = Math.min(index2 + half, length2) - 1;
            // 如果对半比较发现nums1 比较小，那一半可以排除了
            if (nums1[newIndex1] <= nums2[newIndex2]) {
                k -= (newIndex1 - index1 + 1);
                index1 = newIndex1 + 1;
            } else {
                k -= (newIndex2 - index2 + 1);
                index2 = newIndex2 + 1;
            }

        }
    }

    public static void main(String[] args) {
        LC004median_of_two test = new LC004median_of_two();
        int[] num1 = new int[]{ 1, 3 };
        int[] num2 = new int[]{ 2 };
        final double median = test.findMedianSortedArrays(num1, num2);
        System.out.println(median);
    }
}
