package com.hz.yk.base.algo.hot100;

import com.hz.yk.base.algo.camp.ch03_array.ListNode;

/**
 * https://leetcode.cn/problems/remove-nth-node-from-end-of-list/description/
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点
 *
 * @author wuzheng.yk
 * @date 2024/1/26
 */
public class LC019remove_nth {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode pre = new ListNode();
        pre.next = head;
        ListNode right = head, left = pre;
        //右边界先跑出去n-1远的距离
        for (int i = 0; i < n - 1; i++) {
            right = right.next;
        }
        while (right != null && right.next != null) {
            left = left.next;
            right = right.next;
        }
        left.next = left.next.next;
        return pre.next;

    }
}
