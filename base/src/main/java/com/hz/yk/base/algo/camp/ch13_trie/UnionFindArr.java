package com.hz.yk.base.algo.camp.ch13_trie;

/**
 * 使用数组的并查集，性能比hashmap 有数倍提升
 *
 * @author wuzheng.yk
 * @date 2023/10/18
 */
public class UnionFindArr {

    private int[] father;
    private int count;

    public UnionFindArr(int count) {
        this.count = count;
        this.father = new int[count];
        //初始化，每个节点的父类都是自己
        for (int i = 0; i < count; i++) {
            father[i] = i;
        }
    }

    public int find(int element) {
        int root = element;
        while (father[root] != root) {
            root = father[root];
        }

        //进行压缩操作
        while (element != root) {
            int originalFather = father[element];
            father[element] = root;
            element = originalFather;
        }
        return root;
    }

    public void merge(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX != rootY) {
            father[rootY] = rootX;
            count--;
        }
    }

    public boolean isConnected(int x, int y) {
        return find(x) == find(y);
    }

    public int getCount() {
        return count;
    }
}
