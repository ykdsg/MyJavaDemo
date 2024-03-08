package com.hz.yk.base.algo.hot100.practice1;

import com.hz.yk.base.algo.camp.ch03_array.ListNode;

/**
 * https://leetcode.cn/problems/remove-nth-node-from-end-of-list/description/
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点
 *
 * @author wuzheng.yk
 * @date 2024/3/7
 */
public class LC019remove_nth {

    /**
     * pre->1->2->3
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode pre = new ListNode();
        pre.next = head;
        ListNode right = pre, left = pre;
        //先往前跑n步
        for (int i = 0; i < n; i++) {
            right = right.next;
        }
        //将right 移动到最后一个节点，这个时候left 停留在倒数n个节点的前一个位置
        while (right.next != null) {
            right = right.next;
            left = left.next;
        }
        left.next = left.next.next;
        return pre.next;
    }

}
