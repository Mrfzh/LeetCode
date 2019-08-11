package back;

import java.util.ArrayList;
import java.util.List;

public class Question257 {
    public static void main(String[] args) {
        Integer[] values = {1,2,3,null,5};
        TreeNode root = TreeNodeUtil.buildTree(values);
        System.out.println(new Question257().binaryTreePaths(root));
    }

    /**
     * 给定一个二叉树，返回所有从根节点到叶子节点的路径。
     *
     * 说明: 叶子节点是指没有子节点的节点。
     *
     * 回溯
     *
     * @param root
     * @return
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        if (root == null) {
            return res;
        } else if (root.left == null && root.right == null) {
            res.add(String.valueOf(root.val));
            return res;
        } else {
            if (root.left != null) {
                back(res, String.valueOf(root.val), root.left);
            }
            if (root.right != null) {
                back(res, String.valueOf(root.val), root.right);
            }
            return res;
        }
    }

    private void back(List<String> res, String currRoad, TreeNode start) {
        String road = currRoad + "->" + start.val;
        if (start.left == null && start.right == null) {
            res.add(road);
            return;
        }

        if (start.left != null) {
            back(res, road, start.left);
        }
        if (start.right != null) {
            back(res, road, start.right);
        }
    }


}
