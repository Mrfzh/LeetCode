package tree;

public class Question450 {
    public static void main(String[] args) {
        Integer[] values = {5,3,6,2,4,null,7};
        TreeNode root = TreeNodeUtil.buildTree(values);
        TreeNodeUtil.printTree(new Question450().deleteNode(root, 5));
    }

    /**
     * 给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，
     * 并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。
     * 
     * 说明： 要求算法时间复杂度为 O(h)，h 为树的高度。
     *
     * @param root
     * @param key
     * @return
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        TreeNode curr = root;
        TreeNode parent = null;
        while (curr != null) {
            if (curr.val < key) {
                // key 在 curr 的右子树中
                parent = curr;
                curr = curr.right;
            } else if (curr.val > key) {
                // key 在 curr 的左子树中
                parent = curr;
                curr = curr.left;
            } else {
                // 找到了要删除的节点
                // 分几种情况：
                // 1. 要删除的节点是叶子节点，直接删除即可
                // 2. 要删除的节点有一个左孩子或一个右孩子，删除后左（右）孩子顶替它的位置
                // 3. 要删除的节点同时有左孩子和右孩子，右孩子顶替它的位置，左孩子成为右孩子的最左孩子
                if (curr.left == null && curr.right == null) {
                    if (parent == null) {
                        // 说明是根节点，将根节点置空
                        root = null;
                    } else {
                        if (curr.val < parent.val) {
                            parent.left = null;
                        } else {
                            parent.right = null;
                        }
                    }
                } else if (curr.left != null && curr.right != null) {
                    // 删除节点的右孩子顶替它的位置
                    if (parent == null) {
                        // 说明是根节点，根节点变为右孩子
                        root = curr.right;
                    } else {
                        if (curr.val < parent.val) {
                            parent.left = curr.right;
                        } else {
                            parent.right = curr.right;
                        }
                    }
                    // 找到右孩子的最左节点 rl，删除节点的左孩子成为 rl 的左孩子
                    TreeNode rl = curr.right;
                    while (rl.left != null) {
                        rl = rl.left;
                    }
                    rl.left = curr.left;
                } else {
                    // 这时左孩子或右孩子不为空，左（右）孩子顶替删除节点的位置
                    TreeNode temp = curr.left != null? curr.left : curr.right;
                    if (parent == null) {
                        root = temp;
                    } else {
                        if (curr.val < parent.val) {
                            parent.left = temp;
                        } else {
                            parent.right = temp;
                        }
                    }
                }
                // 删除节点后退出循环
                break;
            }
        }
        
        return root;
    }
}
