package com.hz.yk.base.algo.camp.ch03_array;

/**
 * 给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
 *
 * @author wuzheng.yk
 * @date 2023/7/31
 */
public class LC24swap_nodes_in_pairs {

    public ListNode swapPairs(ListNode head) {
        //通过创建一个哨兵
        ListNode pre = new ListNode();
        pre.next = head;

        ListNode cur = pre;
        while (cur.next != null && cur.next.next != null) {
            //要进行交换的2个节点
            final ListNode start = cur.next;
            final ListNode end = cur.next.next;
            cur.next = end;
            start.next = end.next;
            end.next = start;
            cur = start;
        }
        return pre.next;
    }

    //递归写法
    public ListNode swapPairs2(ListNode head){
        if (head == null || head.next == null) {
            return head;
        }
        final ListNode next = head.next;
        head.next = swapPairs2(next.next);
        next.next = head;
        return next;
    }

    public static void main(String[] args) {
        LC24swap_nodes_in_pairs test = new LC24swap_nodes_in_pairs();
        ListNode head = new ListNode(1, 2, 3, 4);
        System.out.println(test.swapPairs(head));

    }
}
