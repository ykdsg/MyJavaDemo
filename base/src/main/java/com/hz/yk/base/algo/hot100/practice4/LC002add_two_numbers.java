package com.hz.yk.base.algo.hot100.practice4;

import com.hz.yk.base.algo.camp.ch03_array.ListNode;

/**
 * https://leetcode.cn/problems/add-two-numbers/description/
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * @author wuzheng.yk
 * @date 2024/2/2
 */
public class LC002add_two_numbers {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode pre = new ListNode();
        int carry = 0;
        ListNode cur = pre;
        while (l1 != null || l2 != null) {
            int i1 = l1 != null ? l1.val : 0;
            int i2 = l2 != null ? l2.val : 0;
            int sum = i1 + i2 + carry;
            carry = sum / 10;
            cur.next = new ListNode(sum % 10);
            l1 = l1 != null ? l1.next : null;
            l2 = l2 != null ? l2.next : null;
            cur = cur.next;
        }
        if (carry > 0) {
            cur.next = new ListNode(carry);
        }
        return pre.next;
    }

}
