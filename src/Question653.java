import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Question653 {
    public static void main(String[] args) {

    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 给定一个二叉搜索树和一个目标结果，如果 BST 中存在两个元素且它们的和等于给定的目标结果，则返回 true。
     *
     * @param root 给定的二叉搜索树
     * @param k 给定的目标结果
     * @return 返回结果
     */
    private static boolean findTarget(TreeNode root, int k) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return false;
        }

        //通过中序遍历得到由小到大的集合
        List<Integer> list = new ArrayList<>();
        findLeaves(list, root);

        //查找
        int i = 0;
        int j = list.size() - 1;
        while (i < j) {
            if (list.get(i) + list.get(j) == k) {
                return true;
            } else if (list.get(i) + list.get(j) > k) {
                j--;
            } else {
                i++;
            }
        }

        return false;
    }

    private static void findLeaves(List<Integer> list, TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        findLeaves(list, treeNode.left);
        list.add(treeNode.val);
        findLeaves(list, treeNode.right);
    }
}
