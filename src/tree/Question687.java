package tree;

import java.util.HashMap;

public class Question687 {
    public static void main(String[] args) {

    }

    /**
     * 给定一个二叉树，找到最长的路径，这个路径中的每个节点具有相同值。
     * 这条路径可以经过也可以不经过根节点。
     *
     * 注意：两个节点之间的路径长度由它们之间的边数表示。
     *
     * 递归（用了两次递归，看了评论，有大佬只用了一次递归，那样更快，不过很难想到）
     *
     * @param root
     * @return
     */
    public int longestUnivaluePath(TreeNode root) {
        cal(root);

        return helper(root);
    }

    private HashMap<TreeNode, Integer> hashMap = new HashMap<>();

    /**
     * 计算从 root 节点出发，最多能有多少个值相同的节点
     */
    private int cal(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftCount = cal(root.left);
        int rightCount = cal(root.right);

        int left = 0;
        int right = 0;
        if (root.left != null && root.val == root.left.val) {
            left = leftCount;
        }
        if (root.right != null && root.val == root.right.val) {
            right = rightCount;
        }

        int res = 1 + Math.max(left, right);
        hashMap.put(root, res);

        return res;
    }

    /**
     * 计算树 root 中的最长同值路径
     */
    private int helper(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = helper(root.left);
        int right = helper(root.right);
        int r = 0;
        if (root.left != null && root.val == root.left.val) {
            r += hashMap.getOrDefault(root.left, 0);
        }
        if (root.right != null && root.val == root.right.val) {
            r += hashMap.getOrDefault(root.right, 0);
        }

        return Math.max(r, Math.max(left, right));
    }
}
