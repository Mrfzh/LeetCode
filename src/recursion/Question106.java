package recursion;

public class Question106 {
    public static void main(String[] args) {

    }

    /**
     * 根据一棵树的中序遍历与后序遍历构造二叉树。
     * 注意:
     * 你可以假设树中没有重复的元素。
     *
     * 递归
     *
     * @param inorder 中序遍历
     * @param postorder 后序遍历
     * @return
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder.length == 0) {
            return null;
        }

        return buildTreeRange(inorder, 0, inorder.length - 1,
                postorder, 0, postorder.length - 1);
    }

    /**
     * 根据中序遍历inorder[inStart, inEnd]和后序遍历postorder[postStart, postEnd]构造二叉树
     */
    private TreeNode buildTreeRange(int[] inorder, int inStart, int inEnd,
                                    int[] postorder, int postStart, int postEnd) {
        //只有一个节点
        if (inStart == inEnd) {
            return new TreeNode(inorder[inStart]);
        }

        //后序遍历的最后一个节点为根节点
        TreeNode res = new TreeNode(postorder[postEnd]);

        //如果中序遍历的第一个节点为根节点，说明没有左子树
        if (inorder[inStart] == postorder[postEnd]) {
            res.right = buildTreeRange(inorder, inStart + 1, inEnd,
                    postorder, postStart, postEnd - 1);
        } else {
            int leftNum = 0;    //左子树的节点数
            for (int i = inStart; i <= inEnd; i++) {
                if (inorder[i] == postorder[postEnd]) {
                    //找到根节点，说明前面的节点为左子树的节点
                    break;
                }
                leftNum++;
            }
            res.left = buildTreeRange(inorder, inStart, inStart + leftNum -1,
                    postorder, postStart, postStart + leftNum - 1);

            int rightNum = inEnd - inStart - leftNum;
            if (rightNum != 0) {
                res.right = buildTreeRange(inorder, inStart + leftNum + 1, inEnd,
                        postorder, postStart + leftNum, postEnd - 1);
            }
        }

        return res;
    }
}
