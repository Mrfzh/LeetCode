package bfs;

import java.util.LinkedList;

public class Question226 {
    public static void main(String[] args) {
        Integer[] values = {4,2,7,1,3,6,9};
        TreeNode root = TreeNodeUtil.buildTree(values);
        TreeNodeUtil.printTree(root);
        TreeNodeUtil.printTree(new Question226().invertTree_2(root));
    }

    /**
     * 翻转一棵二叉树。
     *
     * 递归
     *
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return root;
        }

        TreeNode temp = root.left;
        root.left = invertTree(root.right);
        root.right = invertTree(temp);

        return root;
    }

    /**
     * 非递归：基于 BFS，遍历所有节点，将所有节点的左孩子和右孩子交换即可
     */
    public TreeNode invertTree_2(TreeNode root) {
        LinkedList<TreeNode> queue = new LinkedList<>();
        if (root != null) {
            queue.add(root);
        }
        while (!queue.isEmpty()) {
            TreeNode curr = queue.remove();
            TreeNode left = curr.left;
            TreeNode right = curr.right;
            // 交换左右孩子
            curr.left = right;
            curr.right = left;
            // 将不为空的孩子放入队列
            if (left != null) {
                queue.add(left);
            }
            if (right != null) {
                queue.add(right);
            }
        }

        return root;
    }
}
