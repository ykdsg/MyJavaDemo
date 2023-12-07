package com.hz.yk.base.algo.camp.ch07_recursive;

import com.hz.yk.base.algo.camp.TreeNode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

/**
 * 将一颗树序列化和反序列化
 * 使用递归可以进行深度优先遍历（DFS），整体比较简洁
 *
 * @author wuzheng.yk
 * @date 2023/7/24
 */
public class LC297serialize_and_deserialize_binary_tree {

    //使用递归
    public String serialize(TreeNode root) {
        if (root == null) {
            return "null";
        }
        return root.val + "," + serialize(root.left) + "," + serialize(root.right);

    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        Queue<String> queue = new LinkedList<>(Arrays.asList(data.split(",")));
        return dfs(queue);
    }

    private TreeNode dfs(Queue<String> queue) {
        String val = queue.poll();
        //通过null 进行返回
        if ("null".equals(val)) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(val));
        root.left = dfs(queue);
        root.right = dfs(queue);
        return root;
    }

    //BFS
    public String serializeBFS(TreeNode root) {
        if (root == null) {
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            final TreeNode cur = queue.poll();
            if (cur != null) {
                sb.append(cur.val);
                queue.offer(cur.left);
                queue.offer(cur.right);
            } else {
                sb.append("null");
            }
            sb.append(",");
        }
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }

    public static TreeNode deserializeBFS(String data) {
        if (data == null || Objects.equals(data, "")) {
            return null;
        }
        final String[] dataAr = data.split(",");
        final TreeNode root = new TreeNode(Integer.parseInt(dataAr[0]));
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        int i = 1;
        while (!queue.isEmpty()) {
            final TreeNode cur = queue.poll();
            if (!Objects.equals("null", dataAr[i])) {
                cur.left = new TreeNode(Integer.parseInt(dataAr[i]));
                queue.offer(cur.left);
            }
            i++;
            if (!Objects.equals("null", dataAr[i])) {
                cur.right = new TreeNode(Integer.parseInt(dataAr[i]));
                queue.offer(cur.right);
            }
            i++;
        }

        return root;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);
        //序列化出来是 "1,2,null,null,3,4,null,null,5,null,null"
        LC297serialize_and_deserialize_binary_tree test = new LC297serialize_and_deserialize_binary_tree();
        final String serialize = test.serialize(root);
        System.out.println("serialize1:"+serialize);

        TreeNode treeNode = test.deserialize(serialize);
        System.out.println("deserialize1:"+test.serialize(treeNode));


        final String serialize2 = test.serializeBFS(root);
        TreeNode treeNode2 = test.deserializeBFS(serialize2);
        System.out.println("serialize2:"+test.serializeBFS(treeNode2));
    }
}
