package com.hz.yk.algo.camp.ch12_dynamic;

/**
 * @author wuzheng.yk
 * @date 2023/7/28
 */
public class LC152maximum_product_subarray {
    //乘积跟和不太一样的地方，需要考虑当前位是负数的情况，这个时候需要用到上一位的最小值
    // 还需要考虑上一位计算的最大值为0的情况
    public int maxProduct(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int max, min;
        max = nums[0];
        min = nums[0];
        int result = nums[0];
        for (int i = 1; i < nums.length; i++) {
            //如果当前值小于0，那么max min 的值调换下位置，才能正确计算
            if (nums[i] < 0) {
                int tmp = max;
                max = min;
                min = tmp;
            }
            max = Math.max(max * nums[i], nums[i]);
            min = Math.min(min * nums[i], nums[i]);
            result = Math.max(result, max);
        }
        return result;
    }

    public static void main(String[] args) {
        LC152maximum_product_subarray test = new LC152maximum_product_subarray();
        final int result = test.maxProduct(new int[]{ 2,3,-2,4 });
        System.out.println(result);

    }
}
