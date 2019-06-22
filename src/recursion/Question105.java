package recursion;

public class Question105 {
    public static void main(String[] args) {

    }

    /**
     * 根据一棵树的前序遍历与中序遍历构造二叉树。
     *
     * 注意:
     * 你可以假设树中没有重复的元素。
     *
     * 递归
     *
     * @param preorder 前序遍历
     * @param inorder 中序遍历
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0) {
            return null;
        }

        return buildTreeRange(preorder, 0, preorder.length - 1,
                inorder, 0, inorder.length - 1);
    }

    /**
     * 根据前序遍历preorder[preStart, preEnd]和中序遍历[inStart, inEnd]构造二叉树
     */
    private TreeNode buildTreeRange(int[] preorder, int preStart, int preEnd,
                                    int[] inorder, int inStart, int inEnd) {
        //只有一个节点
        if (preStart == preEnd) {
            return new TreeNode(preorder[preStart]);
        }

        //前序遍历的第一个元素肯定是根节点
        TreeNode res = new TreeNode(preorder[preStart]);

        if (preorder[preStart] != inorder[inStart]) {   //说明左子树不为空
            int leftNum = 0;    //左子树的节点数
            for (int i = inStart; i <= inEnd; i++) {
                if (preorder[preStart] == inorder[i]) {
                    //找到根节点后，说明前面的元素是左子树的中序遍历
                    break;
                }
                leftNum++;
            }
            res.left = buildTreeRange(preorder, preStart + 1, preStart + leftNum,
                    inorder, inStart, inStart + leftNum - 1);

            int rightNum = preEnd - preStart - leftNum;
            if (rightNum != 0) {
                //右子树不为空
                res.right = buildTreeRange(preorder, preStart + leftNum + 1, preEnd,
                        inorder, inStart + leftNum + 1, inEnd);
            }

        } else {
            //此时左子树为空，右子树不为空
            res.right = buildTreeRange(preorder, preStart + 1, preEnd,
                    inorder, inStart + 1, inEnd);
        }

        return res;
    }
}
