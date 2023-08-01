package com.hz.yk.algo.camp.ch04_list;

import org.jetbrains.annotations.Nullable;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 请你设计并实现一个满足  LRU (最近最少使用) 缓存 约束的数据结构。
 * 实现 LRUCache 类：
 * 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
 * https://leetcode.cn/problems/lru-cache/description/
 * 
 * 哈希表查找快，但是数据无固定顺序；链表有顺序之分，插入删除快，但是查找慢。所以结合一下，形成一种新的数据结构
 * @author wuzheng.yk
 * @date 2023/7/31
 */
public class LC146lru_cache {
    static class LRUCache {

        DoubleList list;
        HashMap<Integer, Node> cache;

        int capacity;
        

        /**
         * 以 正整数 作为容量 capacity 初始化 LRU 缓存
         * @param capacity
         */
        public LRUCache(int capacity) {
            list = new DoubleList();
            cache = new HashMap<>();
            this.capacity = capacity;
        }

        /**
         * 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 
         * @param key
         * @return
         */
        public int get(int key) {
            final Node node = cache.get(key);
            if (node == null) {
                return -1;
            }
            //调整顺序
            list.remove(node);
            list.addLast(node);
            return node.val;
        }

        /**
         * 如果关键字 key 已经存在，则变更其数据值 value ；如果不存在，则向缓存中插入该组 key-value 。如果插入操作导致关键字数量超过 capacity ，则应该 逐出 最久未使用的关键字。
         * @param key
         * @param value
         */
        public void put(int key, int value) {
            if (cache.containsKey(key)) {
                Node node = cache.get(key);
                list.remove(node);
            }
            //这里也需要清理cache 里面的key
            if (list.size >= capacity) {
                final Node firstNode = list.removeFirst();
                if (firstNode != null) {
                    cache.remove(firstNode.key);
                }
            }
            final Node newNode = new Node(key, value);
            list.addLast(newNode);
            cache.put(key, newNode);
        }
    }
    
    static class Node{

        public int key, val;
        public Node pre,post;

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }
    
    static class DoubleList{
        private Node head, tail;
        private int size;

        public DoubleList() {
            head = new Node(0, 0);
            tail = new Node(0, 0);
            head.post = tail;
            tail.pre = head;
            size = 0;
        }

        public void addLast(Node node) {
            final Node pre = tail.pre;
            pre.post = node;
            node.pre = pre;
            node.post = tail;
            tail.pre = node;
            size++;
        }
        
        public void remove(Node node) {
            final Node curPre = node.pre;
            final Node curPost = node.post;
            curPre.post = curPost;
            curPost.pre = curPre;
            size--;
        }
        
        @Nullable
        public Node removeFirst() {
            final Node first = head.post;
            if (first == tail) {
                return null;
            }
            final Node newFrist = first.post;
            head.post = newFrist;
            newFrist.pre = head;
            size--;
            return first;
        }
    }

    @Test
    public  void test() {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        assertEquals(1, cache.get(1));
        cache.put(3, 3);
        assertEquals(-1,cache.get(2));
        
    }
}
