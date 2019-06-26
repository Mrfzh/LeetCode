package recursion;

public class Question114 {
    public static void main(String[] args) {

    }

    /**
     * 给定一个二叉树，原地将它展开为链表。
     *
     * 递归
     *
     * @param root
     */
    public void flatten(TreeNode root) {
        flattenAndGetTail(root);
    }

    /**
     * 将二叉树root展开为链表，并返回最后一个节点
     *
     * @param root
     * @return
     */
    private TreeNode flattenAndGetTail(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return root;
        }

        if (root.left == null) {
            //左子树为空
            return flattenAndGetTail(root.right);
        } else if (root.right == null) {
            //右子树为空
            TreeNode tail = flattenAndGetTail(root.left);
            root.right = root.left;
            root.left = null;
            return tail;
        } else {
            //左右子树均不为空
            TreeNode leftTail = flattenAndGetTail(root.left);
            TreeNode rightTail = flattenAndGetTail(root.right);
            leftTail.right = root.right;
            root.right = root.left;
            root.left = null;
            return rightTail;
        }
    }
}
