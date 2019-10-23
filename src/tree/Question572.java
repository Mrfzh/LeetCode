package tree;

public class Question572 {
    public static void main(String[] args) {

    }

    /**
     * 给定两个非空二叉树 s 和 t，检验 s 中是否包含和 t 具有相同结构和节点值的子树。
     * s 的一个子树包括 s 的一个节点和这个节点的所有子孙。s 也可以看做它自身的一棵子树。
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isSubtree(TreeNode s, TreeNode t) {
        ht = heightT(t);
        treeT = t;
        heightS(s);

        return res;
    }

    /**
     * 计算树 t 的高度（假设单个节点的高度为 1）
     */
    private int heightT(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return 1 + Math.max(heightT(root.left), heightT(root.right));
    }

    private int ht;     // 树 t 的高度
    private TreeNode treeT; // 树 t
    private boolean res = false;

    /**
     * 计算树 s 的高度，当高度和树 t 的高度一致时，比较该子树和树 t 是否一样
     */
    private int heightS(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftH = heightS(root.left);
        int rightH = heightS(root.right);
        int height = 1 + Math.max(leftH, rightH);
        if (height == ht && root.val == treeT.val) {
            if (isSame(treeT, root)) {
                res = true;
            }
        }

        return height;
    }

    /**
     * 比较两子树是否完全一样
     */
    private boolean isSame(TreeNode a, TreeNode b) {
        if (a == null && b == null) {
            return true;
        } else if (a != null && b != null) {
            return a.val == b.val && isSame(a.left, b.left) && isSame(a.right, b.right);
        } else {
            return false;
        }
    }
}
