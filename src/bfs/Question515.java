package bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Question515 {
    public static void main(String[] args) {

    }

    /**
     * 您需要在二叉树的每一行中找到最大的值。
     *
     * @param root
     * @return
     */
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        LinkedList<TreeNode> queue = new LinkedList<>();
        if (root != null) {
            queue.add(root);
        }

        while (!queue.isEmpty()) {
            int count = queue.size();   // 本层节点数
            int max = queue.peek().val;   // 本层节点的最大值
            while (count > 0) {
                TreeNode curr = queue.remove();
                max = Math.max(max, curr.val);
                if (curr.left != null) {
                    queue.add(curr.left);
                }
                if (curr.right != null) {
                    queue.add(curr.right);
                }
                count--;
            }
            res.add(max);
        }

        return res;
    }

    /**
     * 递归版本
     */
    public List<Integer> largestValues_recursion(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        helper(root, 0, res);
        return res;
    }

    private void helper(TreeNode root, int depth, List<Integer> res) {
        if (root == null) {
            return;
        }
        if (res.size() < depth+1) {
            res.add(root.val);
        }
        // 更新当前层的最大值
        res.set(depth, Math.max(res.get(depth), root.val));
        helper(root.left, depth+1, res);
        helper(root.right, depth+1, res);
    }
}
