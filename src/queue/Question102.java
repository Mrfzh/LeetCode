package queue;

import java.util.*;

public class Question102 {
    public static void main(String[] args) {
        TreeNode left = new TreeNode(9);
        TreeNode right = new TreeNode(20);
        right.left = new TreeNode(15);
        right.right = new TreeNode(7);
        TreeNode root = new TreeNode(3);
        root.left = left;
        root.right = right;

        System.out.println(new Question102().levelOrder(root));
    }

    /**
     * 给定一个二叉树，返回其按层次遍历的节点值。 （即逐层地，从左到右访问所有节点）。
     *
     * 利用队列
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        List<List<Integer>> res = new ArrayList<>();

        Queue<TreeNode> last = new LinkedList<>();
        last.add(root);

        Queue<TreeNode> curr = new LinkedList<>();
        while (!last.isEmpty()) {
            TreeNode temp;
            List<Integer> list = new ArrayList<>();
            int n = last.size();
            //遍历队列中的元素，并将它们的孩子加入新的队列
            for (int i = 0; i < n; i++) {
                temp = last.remove();
                list.add(temp.val);
                if (temp.left != null) {
                    curr.add(temp.left);
                }
                if (temp.right != null) {
                    curr.add(temp.right);
                }
            }

            last = new LinkedList<>(curr);  //注意：last要深拷贝
            curr.clear();
            res.add(list);
        }

        return res;

    }
}
