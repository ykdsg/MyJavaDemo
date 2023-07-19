package com.hz.yk.algo.camp.ch04_list;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

/**
 *  设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 *  https://leetcode.cn/problems/min-stack/
 * @author wuzheng.yk
 * @date 2023/7/4
 */
public class LC155min_stack {

    class MinStack {

        List<Integer> stack = new ArrayList<>();
        List<Integer> minStack = new ArrayList<>();
        public MinStack() {
            minStack.add(Integer.MAX_VALUE);
        }

        public void push(int val) {
            stack.add(val);
            //注意这里是大于等于，如果没有等于会出现什么情况
            if (minStack.get(minStack.size() - 1) >= val) {
                minStack.add(val);
            }
        }

        public void pop() {
            if (Objects.equals(stack.get(stack.size() - 1), minStack.get(minStack.size() - 1))) {
                minStack.remove(minStack.size() - 1);
            }
            stack.remove(stack.size() - 1);
        }

        public int top() {
            return stack.get(stack.size() - 1);
        }

        public int getMin() {
            return minStack.get(minStack.size() - 1);
        }
    }

    /**
     * 只用1个栈来解决
     */
    class MinStack2 {

        Stack<Integer> stack = new Stack<>();
        int min = Integer.MAX_VALUE; 

        public MinStack2() {
        }

        public void push(int val) {
            if (val <= min) {
                // 将历史min也压入栈
                stack.push(min);
                min = val;
            }
            stack.push(val);
        }

        public void pop() {
            final Integer pop = stack.pop();
            if (pop == min) {
                //如果是最小值出栈，那么弹出下一个栈就是之前存的次小值
                min = stack.pop();
            }
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return min;
        }
    }

    //    如果不使用现成的工具类，使用自定义数据结构来实现
    class MinStack3 {

        Node head;
        public void push(int val) {
            if (null == head) {
                head = new Node(val, val);
            } else {
                final Node cur = new Node(val, Math.min(val, head.min));
                cur.next = head;
                head = cur;
            } 
        }

        public void pop() {
            if (head != null) {
                head = head.next;
            }
        }
        public int top() {
            return head.value;
        }

        public int getMin() {
            return head.min;   
        }
        
        class Node{
            int value;
            int min;
            Node next;

            public Node(int value, int min) {
                this.value = value;
                this.min = min;
                next = null;
            }
        }
    }

    }
