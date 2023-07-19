package com.hz.yk.algo.camp.ch04_list.practice1;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 * https://leetcode.cn/problems/min-stack/
 * @author wuzheng.yk
 * @date 2023/7/19
 */
public class LC155min_stack {

    static class MinStack {

        Deque<Integer> stack = new ArrayDeque();
        Deque<Integer> minStack = new ArrayDeque<>();
        

        public MinStack() {
        }

        public void push(int val) {
            stack.push(val);
            if (minStack.isEmpty() || minStack.peek() >= val) {
                minStack.push(val);
            }
        }

        public void pop() {
            // 这里不能使用==，因为stack.pop 取出来的是Integer 是一个对象地址
            if (stack.pop() <= minStack.peek()) {
                minStack.pop();
            }
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return minStack.peek();
        }
    }

    public static void main(String[] args) {
        MinStack stack = new MinStack();
        stack.push(512);
        stack.push(-1024);
        stack.push(-1024);
        stack.push(512);
        stack.pop();
        System.out.println(stack.getMin());
        stack.pop();
        System.out.println(stack.getMin());
        stack.pop();
        System.out.println(stack.getMin());
        
    }
}
