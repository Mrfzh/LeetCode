package easy;

public class Question563 {
    public static void main(String[] args) {

    }

    /**
     * 给定一个二叉树，计算整个树的坡度。
     * 一个树的节点的坡度定义即为，该节点左子树的结点之和和右子树结点之和的差的绝对值。空结点的的坡度是0。
     * 整个树的坡度就是其所有节点的坡度之和。
     *
     * @param root
     * @return
     */
    public int findTilt(TreeNode root) {
        sum(root);
        return res;
    }

    private int res = 0;

    /**
     * 返回 root 结点及其左右子树之和
     */
    private int sum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftSum = sum(root.left);
        int rightSum = sum(root.right);
        // 结果加上该节点的坡度
        res += Math.abs(leftSum - rightSum);

        return root.val + leftSum + rightSum;
    }
}
