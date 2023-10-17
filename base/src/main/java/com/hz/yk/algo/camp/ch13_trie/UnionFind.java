package com.hz.yk.algo.camp.ch13_trie;

import java.util.HashMap;
import java.util.Map;

/**
 * 并查集相关数据结构
 *
 * @author wuzheng.yk
 * @date 2023/10/17
 */
public class UnionFind {

    //还可以换成数组，这个速度应该更快
    private Map<Integer, Integer> father = new HashMap<>();

    private int count = 0;

    public void add(int x) {
        if (!father.containsKey(x)) {
            father.put(x, null);
            count++;
        }
    }

    /**
     * 查找祖先
     *
     * @param element
     * @return
     */
    public int find(int element) {
        //int root = element;
        //
        //while (father.get(root) != null) {
        //    root = father.get(root);
        //}
        //
        //return root;

        return findAndCompress(element);
    }

    /**
     * 查找并压缩
     *
     * @param element
     * @return
     */
    public int findAndCompress(int element) {
        int root = element;

        while (father.get(root) != null) {
            root = father.get(root);
        }

        while (element != root) {
            int original_father = father.get(element);
            father.put(element, root);
            element = original_father;
        }

        return root;
    }

    public void merge(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX != rootY) {
            father.put(rootY, rootX);
            count--;
        }
    }

    /**
     * 两节点是否连通
     */
    public boolean isConnected(int x, int y) {
        return find(x) == find(y);
    }

    public int getCount() {
        return count;
    }
}
