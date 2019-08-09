package division;

public class Question235 {
    public static void main(String[] args) {

    }

    /**
     * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
     * （一个节点也可以是它自己的祖先）
     *
     * 说明:
     * 1. 所有节点的值都是唯一的。
     * 2. p、q 为不同节点且均存在于给定的二叉搜索树中。
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode curr = root;
        int min = Math.min(p.val, q.val);
        int max = Math.max(p.val, q.val);
        while (curr != null) {
            int currVal = curr.val;
            if (max < currVal) {
                curr = curr.left;
            } else if (min > currVal) {
                curr = curr.right;
            } else {
                return curr;
            }
        }

        return null;
    }
}
