package com.hz.yk.base.rule;

import com.hz.yk.base.rule.fizzbuzz.FizzBuzz;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author wuzheng.yk
 * @date 2025/1/24
 */
public class FizzBuzzTest {

    @Test
    public void num_given_1() {
        //given
        int input = 1;
        //when
        String result = FizzBuzz.count(input);
        //then
        Assertions.assertEquals("1", result);
    }

    @Test
    public void fizz_given_3() {
        //given
        int input = 3;
        //when
        String result = FizzBuzz.count(input);
        //then
        Assertions.assertEquals("Fizz", result);

    }

    @Test
    public void buzz_given_5() {
        //given
        int input = 5;
        //when
        String result = FizzBuzz.count(input);
        //then
        Assertions.assertEquals("Buzz", result);
    }

    @Test
    public void fizz_buzz_given_15() {
        //given
        int input = 15;
        //when
        String result = FizzBuzz.count(input);
        //then
        Assertions.assertEquals("FizzBuzz", result);
    }
}
