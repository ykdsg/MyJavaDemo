package com.hz.yk.base.algo.hot100.practice3;

import com.hz.yk.base.algo.camp.ch03_array.ListNode;

/**
 * https://leetcode.cn/problems/merge-k-sorted-lists/description/
 * 给你一个链表数组，每个链表都已经按升序排列。
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 *
 * @author wuzheng.yk
 * @date 2024/4/22
 */
public class LC023merge_k {

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        return dfs(lists, 0, lists.length - 1);

    }

    private ListNode dfs(ListNode[] lists, int begin, int end) {
        if (begin == end) {
            return lists[begin];
        }
        int mid = begin + (end - begin) / 2;
        final ListNode left = dfs(lists, begin, mid);
        final ListNode right = dfs(lists, mid + 1, end);
        return merge(left, right);
    }

    // 使用递归的方式更简洁
    private ListNode merge(ListNode left, ListNode right) {
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }
        if (left.val <= right.val) {
            left.next = merge(left.next, right);
            return left;
        } else {
            right.next = merge(left, right.next);
            return right;
        }
    }

    private ListNode merge1(ListNode left, ListNode right) {
        ListNode pre = new ListNode();
        ListNode cur = pre;
        while (left != null || right != null) {
            if (left == null) {
                cur.next = right;
                break;
            } else if (right == null) {
                cur.next = left;
                break;
            }
            if (left.val <= right.val) {
                cur.next = left;
                left = left.next;
            } else {
                cur.next = right;
                right = right.next;
            }
            cur = cur.next;
        }
        return pre.next;
    }

}
