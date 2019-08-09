package recursion;

import java.util.LinkedList;

public class Question236 {
    public static void main(String[] args) {

    }

    /**
     * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
     * （一个节点也可以是它自己的祖先）
     *
     * 说明:
     * 1. 所有节点的值都是唯一的。
     * 2. p、q 为不同节点且均存在于给定的二叉搜索树中。
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == p || root == q) {
            return root;
        }
        // 查找左半子树
        boolean hasFoundP = false;
        boolean hasFoundQ = false;
        LinkedList<TreeNode> queue = new LinkedList<>();
        if (root.left != null) {
            queue.add(root.left);
        }
        while (!queue.isEmpty()) {
            TreeNode curr = queue.remove();
            if (curr == p) {
                hasFoundP = true;
            } else if (curr == q) {
                hasFoundQ = true;
            }
            if (hasFoundP && hasFoundQ) {
                break;
            }
            if (curr.left != null) {
                queue.add(curr.left);
            }
            if (curr.right != null) {
                queue.add(curr.right);
            }
        }
        // 判断 p 和 q 的位置
        if (hasFoundP && hasFoundQ) {
            return lowestCommonAncestor(root.left, p, q);
        } else if (!hasFoundP && !hasFoundQ) {
            return lowestCommonAncestor(root.right, p, q);
        } else {
            return root;
        }
    }

    /**
     * 更优化、简洁的写法
     */
    public TreeNode lowestCommonAncestor_2(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || q == root) {
            return root;
        }

        TreeNode left = lowestCommonAncestor_2(root.left, p, q);
        TreeNode right = lowestCommonAncestor_2(root.right, p, q);
        if (left != null && right != null) {
            return root;
        } else if (left != null) {
            return left;
        } else {
            return right;
        }
    }
}
