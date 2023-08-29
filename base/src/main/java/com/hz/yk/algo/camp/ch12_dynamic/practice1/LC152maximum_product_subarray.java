package com.hz.yk.algo.camp.ch12_dynamic.practice1;

/**
 * @author wuzheng.yk
 * @date 2023/7/28
 */
public class LC152maximum_product_subarray {
    //乘积跟和不太一样的地方，需要考虑当前位是负数的情况，这个时候需要用到上一位的最小值
    // 还需要考虑上一位计算的最大值为0的情况
    public int maxProduct(int[] nums) {
        int[] max = new int[nums.length];
        int[] min = new int[nums.length];
        max[0] = nums[0];
        min[0] = nums[0];
        int result = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int maxPre=max[i-1], minPre=min[i-1];
            if (nums[i] < 0) {
                maxPre = min[i - 1];
                minPre = max[i - 1];
            }
            max[i] = Math.max(maxPre * nums[i], nums[i]);
            min[i] = Math.min(minPre * nums[i], nums[i]);
            result = Math.max(result, max[i]);
        }
        return result;
    }

    public static void main(String[] args) {
        LC152maximum_product_subarray test = new LC152maximum_product_subarray();
        final int result = test.maxProduct(new int[]{ 2,3,-2,4 });
        System.out.println(result);

    }
}
