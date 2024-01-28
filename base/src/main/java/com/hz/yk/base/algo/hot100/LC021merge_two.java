package com.hz.yk.base.algo.hot100;

import com.hz.yk.base.algo.camp.ch03_array.ListNode;

/**
 * https://leetcode.cn/problems/merge-two-sorted-lists/description/
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 *
 * @author wuzheng.yk
 * @date 2024/1/27
 */
public class LC021merge_two {

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode pre = new ListNode();
        ListNode cur = pre;
        while (list1 != null || list2 != null) {
            ListNode next;
            if (list1 == null) {
                next = list2;
                list2 = list2.next;
            } else if (list2 == null) {
                next = list1;
                list1 = list1.next;
            } else {
                if (list1.val < list2.val) {
                    next = list1;
                    list1 = list1.next;
                } else {
                    next = list2;
                    list2 = list2.next;
                }
            }
            cur.next = next;
            cur = cur.next;
        }

        return pre.next;
    }

    /**
     * 使用递归的解法
     *
     * @param list1
     * @param list2
     * @return
     */
    public ListNode mergeTwoLists2(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }

        if (list1.val < list2.val) {
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwoLists(list1, list2.next);
            return list2;
        }
    }
}
