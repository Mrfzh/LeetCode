package tree;

import java.util.ArrayList;
import java.util.List;

public class Question655 {
    public static void main(String[] args) {

    }

    /**
     * 在一个 m*n 的二维字符串数组中输出二叉树，并遵守以下规则：
     * 1. 行数 m 应当等于给定二叉树的高度。
     * 2. 列数 n 应当总是奇数。
     * 3. 根节点的值（以字符串格式给出）应当放在可放置的第一行正中间。
     *    根节点所在的行与列会将剩余空间划分为两部分（左下部分和右下部分）。
     *    你应该将左子树输出在左下部分，右子树输出在右下部分。左下和右下部分应当有相同的大小。
     *    即使一个子树为空而另一个非空，你不需要为空的子树输出任何东西，
     *    但仍需要为另一个子树留出足够的空间。然而，如果两个子树都为空则不需要为它们留出任何空间。
     * 4. 每个未使用的空间应包含一个空的字符串""。
     * 5. 使用相同的规则输出子树。
     *
     * 注意: 二叉树的高度在范围 [1, 10] 中。
     *
     * 不难，但是写起来比较长，递归实现
     *
     * @param root
     * @return
     */
    public List<List<String>> printTree(TreeNode root) {
        List<List<String>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        if (root.left == null && root.right == null) {
            List<String> list = new ArrayList<>();
            list.add(String.valueOf(root.val));
            res.add(list);
            return res;
        }

        List<List<String>> leftChild = printTree(root.left);
        List<List<String>> rightChild = printTree(root.right);
        // 计算出左右子树的行数和列数
        int leftRow = leftChild.size();
        int leftCol = leftRow == 0? 0 : leftChild.get(0).size();
        int rightRow = rightChild.size();
        int rightCol = rightRow == 0? 0 : rightChild.get(0).size();
        // 比较行数大小，补充补足的子树
        if (leftRow > rightRow) {
            // 补足右子树
            // 1. 对原有的 rightRow 行，在每个数之间插入 (leftCol - rightCol) / (rightCol + 1) 个 ""
            int num = (leftCol - rightCol) / (rightCol + 1);
            for (int i = 0; i < rightRow; i++) {
                List<String> l1 = new ArrayList<>();
                for (int j = 0; j < rightChild.get(i).size(); j++) {
                    for (int k = 0; k < num; k++) {
                        l1.add("");
                    }
                    l1.add(rightChild.get(i).get(j));
                }
                for (int k = 0; k < num; k++) {
                    l1.add("");
                }
                rightChild.set(i, l1);
            }
            // 2. 补 leftRow - rightRow 行，每行 leftCol 个 ""
            for (int i = 0; i < leftRow - rightRow; i++) {
                List<String> list = new ArrayList<>();
                for (int j = 0; j < leftCol; j++) {
                    list.add("");
                }
                rightChild.add(list);
            }
        } else if (leftRow < rightRow) {
            // 补足左子树，过程和上面一样
            // 1. 对原有的 leftRow 行，在每个数之间插入 (rightCol - leftCol) / (leftCol + 1) 个 ""
            int num = (rightCol - leftCol) / (leftCol + 1);
            for (int i = 0; i < leftRow; i++) {
                List<String> l1 = new ArrayList<>();
                for (int j = 0; j < leftChild.get(i).size(); j++) {
                    for (int k = 0; k < num; k++) {
                        l1.add("");
                    }
                    l1.add(leftChild.get(i).get(j));
                }
                for (int k = 0; k < num; k++) {
                    l1.add("");
                }
                leftChild.set(i, l1);
            }
            // 2. 补 rightRow - leftRow 行，每行 rightCol 个 ""
            for (int i = 0; i < rightRow - leftRow; i++) {
                List<String> list = new ArrayList<>();
                for (int j = 0; j < rightCol; j++) {
                    list.add("");
                }
                leftChild.add(list);
            }
        }

        int n = leftChild.size();
        int m = n == 0? 0 : leftChild.get(0).size();
        // 合并左右子树
        // 1. 第一行需添加 m 个 "" + root.val + m 个 ""
        List<String> list = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            list.add("");
        }
        list.add(String.valueOf(root.val));
        for (int i = 0; i < m; i++) {
            list.add("");
        }
        res.add(list);
        // 2. 之后按顺序添加 m 个左子树元素 + "" + m 个右子树元素
        for (int i = 0; i < n; i++) {
            List<String> curr = new ArrayList<>();
            curr.addAll(leftChild.get(i));
            curr.add("");
            curr.addAll(rightChild.get(i));
            res.add(curr);
        }

        return res;
    }
}
