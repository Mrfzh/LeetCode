package tree;

import java.util.ArrayList;
import java.util.List;

public class Question501 {
    public static void main(String[] args) {

    }

    /**
     * 给定一个有相同值的二叉搜索树（BST），找出 BST 中的所有众数（出现频率最高的元素）。
     *
     * 假定 BST 有如下定义：
     * 1. 结点左子树中所含结点的值小于等于当前结点的值
     * 2. 结点右子树中所含结点的值大于等于当前结点的值
     * 3. 左子树和右子树都是二叉搜索树
     *
     * @param root
     * @return
     */
    public int[] findMode(TreeNode root) {
        if (root == null) {
            return new int[0];
        }

        // 重点：中序遍历结果是一个递增（可能有相等）的序列，可以通过相应变量来记录当前的遍历结果
        find(root);

        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }

        return res;
    }

    private int max = 1;    // 众数出现次数
    private List<Integer> list = new ArrayList<>();    // 众数元素
    private TreeNode lastNode = null;   // 上一遍历节点
    private int count = 0;   // 上一节点的连续出现次数

    /**
     * 进行中序遍历
     */
    private void find(TreeNode root) {
        if (root == null) {
            return;
        }
        find(root.left);

        // 先计算当前节点的连续出现次数
        if (lastNode != null && lastNode.val != root.val) {
            count = 1;
        } else {
            count++;
        }
        // 比较 count 和 max
        if (count == max) {
            list.add(root.val);
        } else if (count > max) {
            list.clear();
            list.add(root.val);
            max = count;
        }
        // 更新 lastNode
        lastNode = root;

        find(root.right);
    }
}
