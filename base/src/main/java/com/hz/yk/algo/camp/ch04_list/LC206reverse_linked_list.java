package com.hz.yk.algo.camp.ch04_list;

/**
 * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
 * @author wuzheng.yk
 * @date 2023/8/1
 */
public class LC206reverse_linked_list {

    public ListNode reverseList(ListNode head) {

        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            final ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    public static void main(String[] args) {
        LC206reverse_linked_list test = new LC206reverse_linked_list();
        ListNode node = new ListNode(1, 2, 3, 4, 5);
        final ListNode result = test.reverseList(node);
        System.out.println(result);
    }
}
