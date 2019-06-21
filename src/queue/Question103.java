package queue;

import java.util.*;

public class Question103 {
    public static void main(String[] args) {

    }

    /**
     * 给定一个二叉树，返回其节点值的锯齿形层次遍历。
     * （即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
     *
     * 队列 + list的反转
     *
     * @param root
     * @return
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        List<List<Integer>> res = new ArrayList<>();
        boolean isReverse = false;

        Queue<TreeNode> curr = new LinkedList<>();
        curr.add(root);
        while (!curr.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            int n = curr.size();
            for (int i = 0; i < n; i++) {
                TreeNode node = curr.remove();
                list.add(node.val);
                if (node.left != null) {
                    curr.add(node.left);
                }
                if (node.right != null) {
                    curr.add(node.right);
                }
            }
            if (isReverse) {
                Collections.reverse(list);
            }
            isReverse = !isReverse;
            res.add(list);
        }

        return res;
    }

    /**
     * 队列 （不用list反转）
     */
    public List<List<Integer>> zigzagLevelOrder2(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        List<List<Integer>> res = new ArrayList<>();
        boolean isReverse = false;

        Queue<TreeNode> curr = new LinkedList<>();
        curr.add(root);
        while (!curr.isEmpty()) {
            LinkedList<Integer> list = new LinkedList<>();
            int n = curr.size();
            for (int i = 0; i < n; i++) {
                TreeNode node = curr.remove();
                //在添加到的时候，判断是头插还是尾插
                if (isReverse) {
                    list.addFirst(node.val);
                } else {
                    list.add(node.val);
                }
                if (node.left != null) {
                    curr.add(node.left);
                }
                if (node.right != null) {
                    curr.add(node.right);
                }
            }
            isReverse = !isReverse;
            res.add(list);
        }

        return res;
    }
}
