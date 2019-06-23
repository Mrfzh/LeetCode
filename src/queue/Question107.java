package queue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Question107 {
    public static void main(String[] args) {

    }

    /**
     * 给定一个二叉树，返回其节点值自底向上的层次遍历。
     * （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
     *
     * 双端队列
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        LinkedList<List<Integer>> res = new LinkedList<>();
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int n = queue.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                TreeNode node = queue.remove();
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
                list.add(node.val);
            }
            res.addFirst(list);
        }

        return res;
    }
}
