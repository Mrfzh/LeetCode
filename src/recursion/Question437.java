package recursion;

public class Question437 {
    public static void main(String[] args) {
        Integer[] values = {1,-2,-3,1,3,null,null,-1};
        TreeNode root = TreeNodeUtil.buildTree(values);
        System.out.println(new Question437().pathSum(root, -2));
    }

    /**
     * 给定一个二叉树，它的每个结点都存放着一个整数值。
     * 找出路径和等于给定数值的路径总数。
     *
     * 路径不需要从根节点开始，也不需要在叶子节点结束，
     * 但是路径方向必须是向下的（只能从父节点到子节点）。
     *
     * 递归
     *
     * @param root
     * @param sum
     * @return
     */
    public int pathSum(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }

        int res = 0;
        // 需要 root
        if (root.val == sum){
            res++;
        }
        res += helper(root.left, sum - root.val) +
                helper(root.right, sum - root.val);

        // 不需要 root
        res += pathSum(root.left, sum) + pathSum(root.right, sum);

        return res;
    }

    /**
     * 找出从 root 出发，路径和为 target 的路径总数（必须包含 root）
     */
    private int helper(TreeNode root, int target) {
        if (root == null) {
            return 0;
        }
        int sum = 0;
        if (root.val == target) {
            sum++;
        }
        sum += helper(root.left, target - root.val) +
                helper(root.right, target - root.val);

        return sum;
    }
}
