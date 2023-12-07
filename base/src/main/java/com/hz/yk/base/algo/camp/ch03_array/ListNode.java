package com.hz.yk.base.algo.camp.ch03_array;

/**
 * @author wuzheng.yk
 * @date 2023/7/31
 */
public class ListNode {

    public int val;
    public ListNode next;

    public ListNode() {}

    public ListNode(int val) {this.val = val;}

    public ListNode(int... vals) {
        if (vals.length < 1) {
            return;
        }
        val = vals[0];
        if (vals.length < 2) {
            return;
        }
        next = new ListNode(vals[1]);

        ListNode tmp = next;
        for (int i = 2; i < vals.length; i++) {
            tmp.next =  new ListNode(vals[i]);
            tmp = tmp.next;
        }
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    @Override
    public String toString() {
        String s = String.valueOf(val);
        ListNode tmp = next;
        while (tmp != null) {
            s += "," +tmp.val;
            tmp = tmp.next;
        }
        return s;
    }

}
