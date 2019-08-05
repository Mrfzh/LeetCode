package division;

import java.util.LinkedList;

public class Question222 {
    public static void main(String[] args) {

    }

    /**
     * 给出一个完全二叉树，求出该树的节点个数。
     *
     * @param root
     * @return
     */
    public int countNodes(TreeNode root) {
        LinkedList<TreeNode> queue = new LinkedList<>();
        if (root != null) {
            queue.add(root);
        }

        int res = 0;
        while (!queue.isEmpty()) {
            TreeNode curr = queue.remove();
            res++;
            if (curr.left != null) {
                queue.add(curr.left);
            }
            if (curr.right != null) {
                queue.add(curr.right);
            }
        }

        return res;
    }

    /**
     * 优化：判断左右子树的高度，如果左子树和右子树的高度相等，
     * 说明左子树是满的，否则说明右子树没有最后一层没有节点
     *
     * 二分思想
     */
    public int countNodes_better(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int ld = getDepth(root.left);
        int rd = getDepth(root.right);
        if (ld == rd) {
            return countNodes_better(root.right) + (1 << ld);
        } else {
            return countNodes_better(root.left) + (1 << rd);
        }
    }

    /**
     * 由于是完全二叉树，可以直接遍历左节点确定高度
     */
    private int getDepth(TreeNode root) {
        TreeNode temp = root;
        int res = 0;
        while (temp != null) {
            res++;
            temp = temp.left;
        }

        return res;
    }
}
