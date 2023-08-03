package com.hz.yk.algo.camp.ch11_binary;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * @author wuzheng.yk
 * @date 2023/8/3
 */
public class LC367valid_perfect_square {

    public boolean isPerfectSquare(int num) {
        long left = 0, right = num;
        while (left <= right) {
            long mid = left + (right - left) / 2;
            final long result = mid * mid;
            
            if (result == num) {
                return true;
            } else if (result > num) {
                right = mid - 1;
            }else {
                left = mid + 1;
            }
        }

        return false;
    }

    @Test
    public void test(){
        LC367valid_perfect_square test = new LC367valid_perfect_square();

        //boolean isTrue = test.isPerfectSquare(16);
        //assertTrue(isTrue);
        //
        //isTrue = test.isPerfectSquare(1);
        //assertTrue(isTrue);
        

        final boolean isFalse = test.isPerfectSquare(2147483647);
        assertFalse(isFalse);
        
        
    }
}
