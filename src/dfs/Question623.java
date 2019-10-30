package dfs;

public class Question623 {
    public static void main(String[] args) {

    }

    /**
     * 给定一个二叉树，根节点为第1层，深度为 1。在其第 d 层追加一行值为 v 的节点。
     *
     * 添加规则：给定一个深度值 d （正整数），针对深度为 d-1 层的每一非空节点 N，
     * 为 N 创建两个值为 v 的左子树和右子树。将 N 原先的左子树，
     * 连接为新左子节点的左子树；将 N 原先的右子树，连接为新右子节点的右子树。
     *
     * 如果 d 的值为 1，深度 d - 1 不存在，则创建一个新的根节点 v，
     * 原先的整棵树将作为 v 的左子树。
     *
     * 注意:
     * 输入的深度值 d 的范围是：[1，二叉树最大深度 + 1]。
     * 输入的二叉树至少有一个节点。
     *
     * @param root
     * @param v
     * @param d
     * @return
     */
    public TreeNode addOneRow(TreeNode root, int v, int d) {
        if (d == 1) {
            TreeNode node = new TreeNode(v);
            node.left = root;
            return node;
        }

        helper(root, 1, v, d);
        return root;
    }

    /**
     * 递归 dfs
     */
    private void helper(TreeNode root, int level, int v, int d) {
        if (root == null) {
            return;
        }
        if (level == d-1) {
            // 为 root 添加两个值为 v 的子节点
            // 原来的左（右）子树成为新左（右）节点的左（右）节点
            TreeNode newLeft = new TreeNode(v);
            newLeft.left = root.left;
            TreeNode newRight = new TreeNode(v);
            newRight.right = root.right;
            root.left = newLeft;
            root.right = newRight;
        } else {
            helper(root.left, level + 1, v, d);
            helper(root.right, level + 1, v, d);
        }
    }
}
