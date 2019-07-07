package recursion;

public class Question129 {
    public static void main(String[] args) {

    }

    private int res = 0;

    /**
     * 给定一个二叉树，它的每个结点都存放一个 0-9 的数字，每条从根到叶子节点的路径都代表一个数字。
     * 例如，从根到叶子节点路径 1->2->3 代表数字 123。
     * 计算从根到叶子节点生成的所有数字之和。
     *
     * 递归
     *
     * @param root
     * @return
     */
    public int sumNumbers(TreeNode root) {
        if (root == null) {
            return 0;
        }

        find(0, root);

        return res;
    }

    /**
     * 从currNode开始查找到叶子节点的路径
     *
     * @param currNum 当前数字（不包括currNode中的数）
     * @param currNode 将要开始遍历的节点
     */
    private void find(int currNum, TreeNode currNode) {
        int newNum = currNum * 10 + currNode.val;
        //如果当前节点是叶子节点
        if (currNode.left == null && currNode.right == null) {
            res += newNum;
            return;
        }
        //非叶子节点的情况
        if (currNode.left == null) {
            //左孩子为空，遍历右子树
            find(newNum, currNode.right);
        } else if (currNode.right == null) {
            //右孩子为空，遍历左子树
            find(newNum, currNode.left);
        } else {
            //都不为空，两边都遍历
            find(newNum, currNode.left);
            find(newNum, currNode.right);
        }
    }
}
