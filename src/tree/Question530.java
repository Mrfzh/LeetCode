package tree;

public class Question530 {
    public static void main(String[] args) {

    }

    /**
     * 给定一个所有节点为非负值的二叉搜索树，求树中任意两节点的差的绝对值的最小值。
     *
     * 中序遍历
     *
     * @param root
     * @return
     */
    public int getMinimumDifference(TreeNode root) {
        // 思路：二叉搜索树的中序遍历是一个递增序列
        // 最小值一定产生于其中两个相邻元素
        inorder(root);

        return min;
    }

    private int last = 0;   // 上一节点的值
    private boolean hasLast = false;    // 是否有上一节点
    private int min = Integer.MAX_VALUE;

    private void inorder(TreeNode root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        if (!hasLast) {
            last = root.val;
            hasLast = true;
        } else {
            min = Math.min(min, root.val - last);
            last = root.val;
        }
        inorder(root.right);
    }
}
