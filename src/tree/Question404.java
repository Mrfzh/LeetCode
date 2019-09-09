package tree;

import javafx.util.Pair;

import java.util.LinkedList;

public class Question404 {
    public static void main(String[] args) {

    }

    /**
     * 计算给定二叉树的所有左叶子之和。
     *
     * @param root
     * @return
     */
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return 0;
        }

        int res = 0;
        // 对于 Pair，key 代表相应节点，value 说明该节点是否为左孩子
        LinkedList<Pair<TreeNode, Boolean>> queue = new LinkedList<>();
        queue.add(new Pair<>(root, false));
        while (!queue.isEmpty()) {
            Pair<TreeNode, Boolean> currPair = queue.remove();
            TreeNode currNode = currPair.getKey();
            // 如果当前节点是左叶子
            if (currPair.getValue() && currNode.left == null
                && currNode.right == null) {
                res += currNode.val;
                continue;
            }
            if (currNode.left != null) {
                queue.add(new Pair<>(currNode.left, true));
            }
            if (currNode.right != null) {
                queue.add(new Pair<>(currNode.right, false));
            }
        }

        return res;
    }

    /**
     * 递归方式
     */
    public int sumOfLeftLeaves_recursion(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int res = 0;
        if (root.left != null && root.left.left == null && root.left.right == null) {
            res += root.left.val;
        }
        res += sumOfLeftLeaves_recursion(root.left) + sumOfLeftLeaves_recursion(root.right);

        return res;
    }
}
