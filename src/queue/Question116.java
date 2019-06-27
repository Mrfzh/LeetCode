package queue;

import java.util.LinkedList;

public class Question116 {
    public static void main(String[] args) {

    }

    /**
     * 给定一个完美二叉树，其所有叶子节点都在同一层，每个父节点都有两个子节点。
     *
     * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。
     * 如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
     * 初始状态下，所有 next 指针都被设置为 NULL。
     *
     * @param root
     * @return
     */
    public Node connect(Node root) {
        if (root == null || root.left == null) {
            return root;
        }

        LinkedList<Node> queue = new LinkedList<>();
        queue.add(root);

        boolean isFinish = false;
        while (!isFinish) {
            int n = queue.size();       //上一层的节点数（父节点）
            for (int i = 0; i < n; i++) {
                Node node = queue.remove();
                if (node.left == null) {
                    //没有下一层后退出循环
                    isFinish = true;
                    break;
                }
                node.left.next = node.right;
                queue.add(node.left);
                node.right.next = (node.next == null)? null : node.next.left;
                queue.add(node.right);
            }
        }

        return root;
    }
}
