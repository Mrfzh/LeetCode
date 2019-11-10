package tree;


public class Question669 {
    public static void main(String[] args) {

    }

    /**
     * 给定一个二叉搜索树，同时给定最小边界L 和最大边界 R。
     * 通过修剪二叉搜索树，使得所有节点的值在[L, R]中 (R>=L) 。
     * 你可能需要改变树的根节点，所以结果应当返回修剪好的二叉搜索树的新的根节点。
     *
     * 老规矩，递归
     *
     * @param root
     * @param L
     * @param R
     * @return
     */
    public TreeNode trimBST(TreeNode root, int L, int R) {
        if (root == null) {
            return null;
        }
        if (root.val < L) {
            return trimBST(root.right, L, R);
        } else if (root.val > R) {
            return trimBST(root.left, L, R);
        } else {
            TreeNode left = trimBST(root.left, L, R);
            TreeNode right = trimBST(root.right, L, R);
            root.left = left;
            root.right = right;
            return root;
        }
    }
}
