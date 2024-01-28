package com.hz.yk.base.algo.hot100;

import com.hz.yk.base.algo.camp.ch03_array.ListNode;

/**
 * https://leetcode.cn/problems/merge-k-sorted-lists/description/
 * 给你一个链表数组，每个链表都已经按升序排列。
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 *
 * @author wuzheng.yk
 * @date 2024/1/27
 */
public class LC023merge_k {

    /**
     * 使用递归的思路，用2个递归方法分别处理：第一个递归解决lists 拆分的问题，第二个递归解决2个listNode合并的问题
     *
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        return merge(lists, 0, lists.length - 1);
    }

    private ListNode merge(ListNode[] lists, int left, int right) {
        if (left == right) {
            return lists[left];
        }
        int mid = left + (right - left) / 2;
        final ListNode l1 = merge(lists, left, mid);
        final ListNode l2 = merge(lists, mid + 1, right);
        return merge2(l1, l2);
    }

    private ListNode merge2(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        if (l1.val < l2.val) {
            l1.next = merge2(l1.next, l2);
            return l1;
        } else {
            l2.next = merge2(l1, l2.next);
            return l2;
        }
    }
}
