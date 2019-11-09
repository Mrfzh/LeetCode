package bfs;

import java.util.LinkedList;

public class Question662 {
    public static void main(String[] args) {

    }

    /**
     * 给定一个二叉树，编写一个函数来获取这个树的最大宽度。
     * 这个二叉树与满二叉树（full binary tree）结构相同，但一些节点为空。
     *
     * 每一层的宽度被定义为两个端点（该层最左和最右的非空节点，
     * 两端点间的null节点也计入长度）之间的长度。
     *
     * bfs
     *
     * @param root
     * @return
     */
    public int widthOfBinaryTree(TreeNode root) {
        int max = 0;    // 结果

        LinkedList<WrappedNode> queue = new LinkedList<>();
        if (root != null) {
            queue.add(new WrappedNode(root, 1));
        }
        while (!queue.isEmpty()) {
            int n = queue.size();   // 该层的节点数
            // 该层最左、最右节点的 pos
            int leftPos = 0;
            int rightPos = 0;
            // 遍历该层节点
            for (int i = 0; i < n; i++) {
                WrappedNode curr = queue.remove();
                if (i == 0) {
                    leftPos = rightPos = curr.pos;
                } else {
                    rightPos = curr.pos;
                }
                // 放入子节点
                if (curr.node.left != null) {
                    queue.add(new WrappedNode(curr.node.left, curr.pos * 2 - 1));
                }
                if (curr.node.right != null) {
                    queue.add(new WrappedNode(curr.node.right, curr.pos * 2));
                }
            }
            // 计算该层的宽度
            int len = rightPos - leftPos + 1;
            // 更新 max
            max = Math.max(max, len);
        }

        return max;
    }

    class WrappedNode {
        TreeNode node;
        int pos;   // 该 TreeNode 在其所在层的位置索引
        // 注：每一层的索引由 1 开始，假设父节点的 pos 为 pp，那么它的左孩子的 pos 为 pp * 2 - 1
        // 它的右孩子的 pos 为 pp * 2

        WrappedNode(TreeNode node, int pos) {
            this.node = node;
            this.pos = pos;
        }
    }
}
