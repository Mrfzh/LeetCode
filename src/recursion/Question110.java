package recursion;

public class Question110 {
    public static void main(String[] args) {

    }

    /**
     * 给定一个二叉树，判断它是否是高度平衡的二叉树。
     * 本题中，一棵高度平衡二叉树定义为：一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。
     *
     * 递归
     *
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }

        return isBalanced(root.left) && isBalanced(root.right) &&
                (Math.abs(length(root.left) - length(root.right)) <= 1);
    }

    private int length(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return Math.max(length(root.left), length(root.right)) + 1;
    }

    /**
     * 优化
     */
    public boolean isBalanced2(TreeNode root) {
        if (root == null) {
            return true;
        }

        return length2(root) != -1;
    }

    /**
     * 优化后的求深度：发现左子树不是平衡二叉树后就无需求右子树的深度
     */
    private int length2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = length2(root.left);
        if (left == -1) {
            return -1;
        }
        int right = length2(root.right);
        if (right == -1) {
            return -1;
        }
        return Math.abs(left - right) <= 1? Math.max(left, right) + 1 : -1;
    }
}
