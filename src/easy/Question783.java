package easy;

public class Question783 {
    public static void main(String[] args) {

    }

    /**
     * 给定一个二叉搜索树的根结点 root, 返回树中任意两节点的差的最小值。
     *
     * 注意：
     * 二叉树的大小范围在 2 到 100。
     * 二叉树总是有效的，每个节点的值都是整数，且不重复。
     *
     * @param root
     * @return
     */
    public int minDiffInBST(TreeNode root) {
        helper(root);

        return min;
    }

    private int min = Integer.MAX_VALUE;
    private boolean hasLast = false;
    private int last;

    private void helper(TreeNode root) {
        if (root == null) {
            return;
        }
        helper(root.left);
        if (!hasLast) {
            last = root.val;
            hasLast = true;
        } else {
            min = Math.min(min, Math.abs(root.val - last));
            last = root.val;
        }
        helper(root.right);
    }
}
