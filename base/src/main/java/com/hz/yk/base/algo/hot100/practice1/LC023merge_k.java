package com.hz.yk.base.algo.hot100.practice1;

import com.hz.yk.base.algo.camp.ch03_array.ListNode;

/**
 * https://leetcode.cn/problems/merge-k-sorted-lists/description/
 * 给你一个链表数组，每个链表都已经按升序排列。
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 *
 * @author wuzheng.yk
 * @date 2024/3/3
 */
public class LC023merge_k {

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        return dfs(lists, 0, lists.length - 1);
    }

    private ListNode dfs(ListNode[] lists, int start, int end) {
        if (start == end) {
            return lists[start];
        }
        int mid = start + (end - start) / 2;
        final ListNode h1 = dfs(lists, start, mid);
        final ListNode h2 = dfs(lists, mid + 1, end);

        return merge(h1, h2);
    }

    private ListNode merge(ListNode h1, ListNode h2) {
        if (h1 == null) {
            return h2;
        }
        if (h2 == null) {
            return h1;
        }
        if (h1.val <= h2.val) {
            h1.next = merge(h1.next, h2);
            return h1;
        } else {
            h2.next = merge(h1, h2.next);
            return h2;
        }
    }

}
