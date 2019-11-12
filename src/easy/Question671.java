package easy;

import java.util.LinkedList;

public class Question671 {
    public static void main(String[] args) {

    }

    /**
     * 给定一个非空特殊的二叉树，每个节点都是正数，并且每个节点的子节点数量只能为 2 或 0。
     * 如果一个节点有两个子节点的话，那么这个节点的值不大于（<=）它的子节点的值。 
     *
     * 给出这样的一个二叉树，你需要输出所有节点中的第二小的值。如果第二小的值不存在的话，输出 -1 。
     *
     * bfs，比较简单，注意提前结束的条件
     *
     * @param root
     * @return
     */
    public int findSecondMinimumValue(TreeNode root) {
        // 注意：这种树的前序遍历不一定是非递减序列

        // 可以使用层序遍历来做，根节点一定为第一小的值 min，
        // 遍历每一层的节点，如果该层的节点有大于 min 的（可能有多个，找到最小的一个）
        // 那么该节点的值就是第二小的值，到此层遍历提前结束

        int min = 0;
        LinkedList<TreeNode> queue = new LinkedList<>();
        if (root != null) {
            queue.add(root);
            min = root.val;
        }
        int secondMin = Integer.MIN_VALUE;  // 第二小的数
        while (!queue.isEmpty()) {
            int n = queue.size();   // 该层的节点数
            int levelMin = Integer.MAX_VALUE;   // 记录该层的最小数
            for (int i = 0; i < n; i++) {
                TreeNode curr = queue.remove();
                if (curr.left != null) {
                    queue.add(curr.left);
                }
                if (curr.right != null) {
                    queue.add(curr.right);
                }
                // 更新 secondMin
                if (curr.val > min) {
                    if (secondMin == Integer.MIN_VALUE) {
                        secondMin = curr.val;
                    } else {
                        secondMin = Math.min(secondMin, curr.val);
                    }
                }
                levelMin = Math.min(curr.val, levelMin);
            }
            // 如果该层的最小数都大于等于 secondMin，提前结束
            if (secondMin != Integer.MIN_VALUE && levelMin >= secondMin) {
                return secondMin;
            }
        }

        return secondMin == Integer.MIN_VALUE ? -1 : secondMin;
    }
}
