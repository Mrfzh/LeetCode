package hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Question508 {
    public static void main(String[] args) {

    }

    /**
     * 给出二叉树的根，找出出现次数最多的子树元素和。
     * 一个结点的子树元素和定义为以该结点为根的二叉树上所有结点的元素之和（包括结点本身）。
     * 然后求出出现次数最多的子树元素和。
     * 如果有多个元素出现的次数相同，返回所有出现次数最多的元素（不限顺序）。
     *
     * hash
     *
     * @param root
     * @return
     */
    public int[] findFrequentTreeSum(TreeNode root) {
        // 计算各节点的子树元素和
        sum(root);

        // 存储出现次数最高的子树元素和
        List<Integer> list = new ArrayList<>();
        for (int sum : countMap.keySet()) {
            if (maxCount == countMap.get(sum)) {
                list.add(sum);
            }
        }
        // 转换为数组
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }

        return res;
    }

    // 该 HashMap 的 key 为根节点，value 为该节点的子树元素和
    private HashMap<TreeNode, Integer> sumMap = new HashMap<>();
    // 该 HashMap 的 key 为子树元素和，value 为 key 的出现次数
    private HashMap<Integer, Integer> countMap = new HashMap<>();
    // 记录最高次数
    private int maxCount = 0;

    /**
     * 返回 root 节点的子树元素和
     */
    private int sum(TreeNode root) {
        if (root == null) {
            return 0;
        }

        // 当前节点的子树元素和
        int currSum = sum(root.left) + sum(root.right) + root.val;
        // 更新相关记录
        sumMap.put(root, currSum);
        int count;
        if (countMap.containsKey(currSum)) {
            count = countMap.get(currSum) + 1;
        } else {
            count = 1;
        }
        countMap.put(currSum, count);
        maxCount = Math.max(maxCount, count);
        return currSum;
    }
}
