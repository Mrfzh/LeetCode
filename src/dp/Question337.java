package dp;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

public class Question337 {
    public static void main(String[] args) {

    }

    /**
     * 在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。
     * 这个地区只有一个入口，我们称之为“根”。 除了“根”之外，
     * 每栋房子有且只有一个“父“房子与之相连。
     * 一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。
     * 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。
     *
     * 计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。
     *
     * dp（借助了栈、队列和 hashMap）
     *
     * @param root
     * @return
     */
    public int rob(TreeNode root) {
//        // 递归超时
//        return robImpl(false, root);

        if (root == null) {
            return 0;
        }

        // 广度优先遍历，并将结果存入栈中
        Stack<TreeNode> nodeStack = new Stack<>();
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode curr = queue.remove();
            nodeStack.push(curr);
            if (curr.left != null) {
                queue.add(curr.left);
            }
            if (curr.right != null) {
                queue.add(curr.right);
            }
        }

        // 用 Map 存储从某节点开始盗窃所得的最高金额（分为盗窃了该节点和未盗窃该节点两种情况）
        HashMap<TreeNode, Integer> robMap = new HashMap<>();
        HashMap<TreeNode, Integer> notRobMap = new HashMap<>();
        while (!nodeStack.isEmpty()) {
            TreeNode curr = nodeStack.pop();
            if (curr.left == null && curr.right == null) {
                robMap.put(curr, curr.val);
                notRobMap.put(curr, 0);
                continue;
            }
            // 盗窃了该节点的情况
            int robAccount = curr.val;
            if (curr.left != null) {
                robAccount += notRobMap.get(curr.left);
            }
            if (curr.right != null) {
                robAccount += notRobMap.get(curr.right);
            }
            robMap.put(curr, robAccount);
            // 没有盗窃该节点的情况
            int notRobAccount = 0;
            if (curr.left != null) {
                notRobAccount += Math.max(
                        notRobMap.get(curr.left), robMap.get(curr.left));
            }
            if (curr.right != null) {
                notRobAccount += Math.max(
                        notRobMap.get(curr.right), robMap.get(curr.right));
            }
            notRobMap.put(curr, notRobAccount);
        }

        return Math.max(robMap.get(root), notRobMap.get(root));
    }

    /**
     * 从 currRob 节点开始盗窃，返回盗窃所得的最高金额
     *
     * @param hasRobParent 父节点是否有被盗窃
     * @param currRob 当前盗窃节点
     * @return
     */
    private int robImpl(boolean hasRobParent, TreeNode currRob) {
        if (currRob == null) {
            return 0;
        }

        // 当前节点不盗窃所能获得的最大利润
        int notRob = robImpl(false, currRob.left) +
                robImpl(false, currRob.right);

        // 判断父节点是否有盗窃
        if (hasRobParent) {
            return notRob;
        } else {
            // 当前节点盗窃所能获得的最大利润
            int rob = currRob.val + robImpl(true, currRob.left) +
                    robImpl(true, currRob.right);
            return Math.max(notRob, rob);
        }
    }
}
