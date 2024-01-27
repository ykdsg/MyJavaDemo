package com.hz.yk.base.algo.hot100.practice1;

import com.hz.yk.base.algo.camp.ch03_array.ListNode;

/**
 * https://leetcode.cn/problems/remove-nth-node-from-end-of-list/description/
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点
 *
 * @author wuzheng.yk
 * @date 2024/1/27
 */
public class LC019remove_nth {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode pre = new ListNode();
        pre.next = head;
        ListNode left = pre, right = pre;
        for (int i = 0; i < n; i++) {
            right = right.next;
        }
        while (right != null && right.next != null) {
            right = right.next;
            left = left.next;
        }
        left.next = left.next.next;
        return pre.next;
    }
}
