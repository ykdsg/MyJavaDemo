package com.hz.yk.base.algo.hot100;

/**
 * https://leetcode.cn/problems/single-number/description/
 * 给你一个 非空 整数数组 nums ，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 * 你必须设计并实现线性时间复杂度的算法来解决此问题，且该算法只使用常量额外空间。
 *
 * @author wuzheng.yk
 * @date 2024/3/26
 */
public class LC136single_number {

    public int singleNumber(int[] nums) {
        int result = 0;
        for (int num : nums) {
            // 使用异或将2个相同的值进行消除，最后得到的就是唯一那个元素
            result ^= num;
        }
        return result;
    }
}
