public class Question112 {
    public static void main(String[] args) {

    }

    /**
     * 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，
     * 这条路径上所有节点值相加等于目标和。
     * 说明: 叶子节点是指没有子节点的节点。
     *
     * 递归
     *
     * @param root
     * @param sum
     * @return
     */
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        return find(root, 0, sum);
    }

    /**
     * 从root节点开始寻找，看能否找到一条到叶子节点，且节点和为sum的路径
     *
     * @param root 当前开始节点
     * @param currSum 当前总和
     * @param sum 需要总和
     * @return
     */
    private boolean find(TreeNode root, int currSum, int sum) {
        if (root == null) {
            return currSum == sum;
        }
        if (root.left == null && root.right == null) {
            return (currSum + root.val) == sum;
        } else if (root.left == null) {
            return find(root.right, root.val+currSum, sum);
        } else if (root.right == null) {
            return find(root.left, root.val+currSum, sum);
        } else {
            if (find(root.left, root.val+currSum, sum)) {
                return true;
            } else {
                return find(root.right, root.val+currSum, sum);
            }
        }
    }
}
