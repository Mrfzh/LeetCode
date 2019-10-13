package tree;

public class Question538 {
    public static void main(String[] args) {

    }

    /**
     * 给定一个二叉搜索树（Binary Search Tree），把它转换成为累加树（Greater Tree)，
     * 使得每个节点的值是原来的节点值加上所有大于它的节点值之和。
     *
     * @param root
     * @return
     */
    public TreeNode convertBST(TreeNode root) {
        inorder(root);

        return root;
    }

    private int sum = 0;    // 当前累加和

    /**
     * 先遍历右子树的中序遍历
     */
    private void inorder(TreeNode root) {
        if (root == null) {
            return;
        }
        inorder(root.right);
        root.val = root.val + sum;
        sum = root.val;
        inorder(root.left);
    }
}
