package tree;

public class Question543 {
    public static void main(String[] args) {

    }

    /**
     * 给定一棵二叉树，你需要计算它的直径长度。
     * 一棵二叉树的直径长度是任意两个结点之间路径长度的最大值。这条路径可能穿过根结点。
     *
     * 注意：两结点之间的路径长度是以它们之间边的数目表示。
     *
     * @param root
     * @return
     */
    public int diameterOfBinaryTree(TreeNode root) {
        getMaxLen(root);
        return max;
    }

    private int max = 0;

    /**
     * 返回以 root 为起点的最长路径的长度（以节点个数计算）
     */
    private int getMaxLen(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftMaxLen = getMaxLen(root.left);
        int rightMaxLen = getMaxLen(root.right);
        // 判断是否需要更新 max
        if (leftMaxLen + rightMaxLen > max) {
            max = leftMaxLen + rightMaxLen;
        }
        return Math.max(leftMaxLen, rightMaxLen) + 1;
     }
}
