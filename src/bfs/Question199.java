package bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Question199 {
    public static void main(String[] args) {
        Integer[] values = {1,2,3,null,5,null,4};
        TreeNode root = TreeNodeUtil.buildTree(values);
        System.out.println(new Question199().rightSideView(root));
    }

    /**
     * 给定一棵二叉树，想象自己站在它的右侧，
     * 按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
     *
     * bfs
     *
     * @param root
     * @return
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        // bfs，记录每一层的最后一个元素
        TreeNode end = root;
        TreeNode lastEnd = root;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        res.add(root.val);
        while (!queue.isEmpty()) {
            TreeNode curr = queue.remove();
            if (curr.left != null) {
                queue.add(curr.left);
                end = curr.left;
            }
            if (curr.right != null) {
                queue.add(curr.right);
                end = curr.right;
            }
            if (end != lastEnd && curr == lastEnd) {
                res.add(end.val);
                lastEnd = end;
            }
        }

        return res;
    }
}
