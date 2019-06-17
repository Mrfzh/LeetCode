package recursion;

import java.util.ArrayList;
import java.util.List;

public class Question95 {
    public static void main(String[] args) {

    }

    /**
     * 给定一个整数 n，生成所有由 1 ... n 为节点所组成的二叉搜索树。
     *
     * 递归
     *
     * @param n
     * @return
     */
    public List<TreeNode> generateTrees(int n) {
        if (n < 1) {
            return new ArrayList<>();
        }

        return generateTreesRange(1, n);
    }

    /**
     * 生成由start...end组成的二叉查找树集合
     */
    private List<TreeNode> generateTreesRange(int start, int end) {
        List<TreeNode> res = new ArrayList<>();
        if (start == end) {
            TreeNode treeNode = new TreeNode(start);
            res.add(treeNode);
            return res;
        }

        //单独讨论头结点是start或end的情况
        List<TreeNode> other = generateTreesRange(start + 1, end);
        for (int i = 0; i < other.size(); i++) {
            TreeNode treeStart = new TreeNode(start);
            treeStart.right = other.get(i);
            res.add(treeStart);
        }
        other = generateTreesRange(start, end - 1);
        for (int i = 0; i < other.size(); i++) {
            TreeNode treeEnd = new TreeNode(end);
            treeEnd.left = other.get(i);
            res.add(treeEnd);
        }
        //其他情况
        for (int i = start + 1; i < end; i++) {
            List<TreeNode> otherLeft = generateTreesRange(start , i - 1);
            List<TreeNode> otherRight = generateTreesRange(i + 1, end);
            for (int j = 0; j < otherLeft.size(); j++) {
                for (int k = 0; k < otherRight.size(); k++) {
                    TreeNode tree = new TreeNode(i);
                    tree.left = otherLeft.get(j);
                    tree.right = otherRight.get(k);
                    res.add(tree);
                }
            }
        }

        return res;
    }
}
