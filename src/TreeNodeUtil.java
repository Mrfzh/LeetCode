import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TreeNodeUtil {

    /**
     * 构建二叉树
     *
     * @param arrays
     * @return
     */
    public static TreeNode buildTree(Integer[] arrays) {
        if (arrays.length == 0) {
            return null;
        } else if (arrays[0] == null) {
            return null;
        }

        LinkedList<TreeNode> queue = new LinkedList<>();
        TreeNode head = new TreeNode(arrays[0]);
        queue.add(head);
        for (int i = 1; i < arrays.length; ) {
            TreeNode curr = queue.remove();
            if (arrays[i] != null) {
                TreeNode left = new TreeNode(arrays[i]);
                curr.left = left;
                queue.add(left);
            }
            i++;
            if (i < arrays.length && arrays[i] != null) {
                TreeNode right = new TreeNode(arrays[i]);
                curr.right = right;
                queue.add(right);
            }
            i++;
        }

        return head;
    }

    /**
     * 打印二叉树
     *
     * @param head 二叉树的头结点
     */
    public static void printTree(TreeNode head) {
        List<Integer> res = new ArrayList<>();
        print(res, head);
        System.out.println(res);
    }

    private static void print(List<Integer> res, TreeNode head) {
        if (head == null) {
            return;
        }

        res.add(head.val);
        print(res, head.left);
        print(res, head.right);
    }
}
