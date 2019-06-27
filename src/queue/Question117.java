package queue;

import java.util.LinkedList;

public class Question117 {
    public static void main(String[] args) {

    }

    /**
     * 给定一个二叉树，填充它的每个 next 指针，让这个指针指向其下一个右侧节点。
     * 如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
     *
     * 初始状态下，所有 next 指针都被设置为 NULL。
     *
     * @param root
     * @return
     */
    public Node connect(Node root) {
        if (root == null || (root.left == null && root.right == null)) {
            return root;
        }

        LinkedList<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int n = queue.size();
            for (int i = 0; i < n; i++) {
                Node node = queue.remove();
                if (node.left != null) {
                    node.left.next = (node.right != null)? node.right : find(node);
                    queue.add(node.left);
                }
                if (node.right != null) {
                    node.right.next = find(node);
                    queue.add(node.right);
                }
            }
        }

        return root;
    }

    /**
     * 在node节点的右侧节点中寻找非空子节点
     */
    private Node find(Node node) {
        Node temp = node.next;
        while (temp != null) {
            if (temp.left != null) {
                return temp.left;
            } else if (temp.right != null) {
                return temp.right;
            }
            temp = temp.next;
        }

        return null;
    }
}
