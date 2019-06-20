package recursion;

public class Question101 {
    public static void main(String[] args) {

    }

    private StringBuilder left = new StringBuilder();
    private StringBuilder right = new StringBuilder();

    /**
     * 给定一个二叉树，检查它是否是镜像对称的。
     *
     * 递归
     * 思路：只要左子树中左右遍历和右子树中右左遍历的结果一样，就是对称的。（可利用栈转化为迭代解法）
     *
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        travelLeft(root.left);
        travelRight(root.right);

        return left.toString().equals(right.toString());
    }

    private void travelLeft(TreeNode root) {
        if (root == null) {
            left.append(0);
            return;
        }

        left.append(root.val);
        travelLeft(root.left);
        travelLeft(root.right);
    }

    private void travelRight(TreeNode root) {
        if (root == null) {
            right.append(0);
            return;
        }

        right.append(root.val);
        travelRight(root.right);
        travelRight(root.left);
    }
}
