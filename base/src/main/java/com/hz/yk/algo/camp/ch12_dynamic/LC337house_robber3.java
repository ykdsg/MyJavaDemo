package com.hz.yk.algo.camp.ch12_dynamic;

import com.hz.yk.algo.camp.TreeNode;

/**
 * 小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为 root 。
 * 除了 root 之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果 两个直接相连的房子在同一天晚上被打劫 ，房屋将自动报警。
 * @author wuzheng.yk
 * @date 2023/7/29
 */
public class LC337house_robber3 {

    /**
     * 树形结构还是更适合使用递归，动态规划有点费劲了
     * @param root
     * @return
     */
    public int rob(TreeNode root) {
        final int[] result = dp(root);
        return Math.max(result[0], result[1]);
    }

    /**
     * 这里巧妙的使用数组返回多维的信息
     * 返回数组，0 表示不选父节点的最大值，1表示选择父节点的最大值
     * @param root
     * @return
     */
    int[] dp(TreeNode root) {
        int[] result = new int[2];
        if (root == null) {
            return result;
        }
        final int[] left = dp(root.left);
        final int[] right = dp(root.right);

        //父节点不选的情况下，选择各个子节点最大值
        result[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        result[1] = root.val + left[0] + right[0];
        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(4, 1, 3);
        root.right = new TreeNode(5,1,1);
        
        LC337house_robber3 test = new LC337house_robber3();
        final int res = test.rob(root);
        System.out.println(res);

    }
}
