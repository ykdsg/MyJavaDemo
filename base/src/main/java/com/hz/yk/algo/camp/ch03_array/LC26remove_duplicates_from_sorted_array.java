package com.hz.yk.algo.camp.ch03_array;

/**
 * 给你一个 升序排列 的数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。元素的 相对顺序 应该保持 一致 。然后返回 nums 中唯一元素的个数。
 * 
 * @author wuzheng.yk
 * @date 2023/8/1
 */
public class LC26remove_duplicates_from_sorted_array {

    public int removeDuplicates(int[] nums) {
        int cur = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                nums[cur] = nums[i];
                cur++;
            }
        }
        return cur;
    }
}
