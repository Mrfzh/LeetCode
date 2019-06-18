package tree;

import java.util.ArrayList;
import java.util.List;

public class Question98 {
    public static void main(String[] args) {

    }

    private List<Integer> orderList = new ArrayList<>();

    /**
     * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
     *
     * 假设一个二叉搜索树具有如下特征：
     * 1. 节点的左子树只包含小于当前节点的数。
     * 2. 节点的右子树只包含大于当前节点的数。
     * 3. 所有左子树和右子树自身必须也是二叉搜索树。
     *
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }

        find(root);

        int last = orderList.get(0);
        for (int i = 1; i < orderList.size(); i++) {
            if (last >= orderList.get(i)) {
                return false;
            }
            last = orderList.get(i);
        }

        return true;
    }

    /**
     * 二叉树进行中序遍历，结果存入集合
     *
     * @param root
     */
    private void find(TreeNode root) {
        if (root.left == null && root.right == null) {
            orderList.add(root.val);
            return;
        }

        if (root.left != null) {
            find(root.left);
        }
        orderList.add(root.val);
        if (root.right != null) {
            find(root.right);
        }
    }


    long maxVal = Long.MIN_VALUE;

    /**
     * 改进后的方法：利用一个变量来记录中序遍历的上一个遍历值
     */
    public boolean isValidBST_2(TreeNode root) {
        if (root == null)
            return true;

        if (isValidBST(root.left)) {
            if (maxVal < root.val) {
                maxVal = root.val;
                return isValidBST(root.right);
            }
        }
        return false;
    }
}
