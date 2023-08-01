package com.hz.yk.algo.camp.ch04_list;

import java.util.Arrays;

/**
 * @author wuzheng.yk
 * @date 2023/8/1
 */
public class LC66plus_one {

    public int[] plusOne(int[] digits) {
        final int n = digits.length;
        for (int i = n - 1; i >= 0; i--) {
            if (digits[i] != 9) {
                digits[i]++;
                break;
            } else {
                if (i == 0) {
                    digits = new int[digits.length + 1];
                    digits[0] = 1;
                } else {
                    digits[i] = 0;
                }
            }
        }
        return digits;
    }

    public static void main(String[] args) {
        LC66plus_one test = new LC66plus_one();
        final int[] ints = { 9, 9, 9 };
        final int[] result = test.plusOne(ints);
        System.out.println(Arrays.toString(result));
    }
}
