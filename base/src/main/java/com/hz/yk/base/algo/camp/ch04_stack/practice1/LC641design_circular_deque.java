package com.hz.yk.base.algo.camp.ch04_stack.practice1;

/**
 * 设计实现循环双端队列
 *
 * @author wuzheng.yk
 * @date 2023/7/19
 */
public class LC641design_circular_deque {


    //核心要点是首先数组长度是要求的长度+1，这样能够方便区分是因为队列满还是空的情况
    //需要先想明白队列为空和为满的情况
    //头尾指针移动的时候，都需要考虑循环的问题，因此+capacity，然后再取模能够很好的解决循环之后指针的位置问题。
    class MyCircularDeque {
        int capacity;

        int[] cache;
        /**
         * 头部第一个有效数据的位置
         */
        int front;
        /**
         * 尾部可放入的有效的位置
         */
        int rear;

        public MyCircularDeque(int k) {
            capacity = k + 1;
            //为了避免判断为空和为满的情况，这里多空出1个
            cache = new int[capacity];
            front = 0;
            rear = 0;
        }

        public boolean insertFront(int value) {
            if (isFull()) {
                return false;
            }
            //这里要加上长度然后取模，因为循环过来之后会到数组尾部
            front = (front - 1 + capacity) % capacity;
            cache[front] = value;
            return true;
        }

        public boolean insertLast(int value) {
            if (isFull()) {
                return false;
            }
            cache[rear] = value;
            rear = (rear + 1 + capacity) % capacity;
            return true;
        }

        public boolean deleteFront() {
            if (isEmpty()) {
                return false;
            }
            front = (front + 1 + capacity) % capacity;
            return true;
        }

        public boolean deleteLast() {
            if (isEmpty()) {
                return false;
            }
            rear = (rear - 1 + capacity) % capacity;
            return true;
        }

        public int getFront() {
            if (isEmpty()) {
                return -1;
            }
            return cache[front];

        }

        public int getRear() {
            if (isEmpty()) {
                return -1;
            }
            final int last = (rear - 1 + capacity) % capacity;
            return cache[last];

        }

        public boolean isEmpty() {
            //为空的情况
            return front == rear;
        }

        public boolean isFull() {
            //如果尾部指针跟头部指针只差1位，那说明就满了
            return (rear + 1) % capacity == front;
        }
    }
}
