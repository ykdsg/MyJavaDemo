package com.hz.yk.base.algo.hot100.practice3;

import com.hz.yk.base.algo.camp.ch03_array.ListNode;

/**
 * https://leetcode.cn/problems/add-two-numbers/description/
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * @author wuzheng.yk
 * @date 2024/1/26
 */
public class LC002add_two_numbers {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode pre = new ListNode(0);
        ListNode cur = pre;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int index1 = l1 == null ? 0 : l1.val;
            int index2 = l2 == null ? 0 : l2.val;
            int sum = index1 + index2 + carry;
            cur.next = new ListNode(sum % 10);
            cur = cur.next;
            carry = sum / 10;
            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
        }
        if (carry == 1) {
            cur.next = new ListNode(1);
        }
        return pre.next;
    }
}
